package tn.esprit.spring.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SkierServicesImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkierServiceImplTest {

    @Mock
    private ISkierRepository skierRepository;
    @Mock
    private ISubscriptionRepository subscriptionRepository;
    @Mock
    private ICourseRepository courseRepository;
    @Mock
    private IPisteRepository pisteRepository;
    @InjectMocks
    private SkierServicesImpl skierServices;

    @Test //done
    public void testRetrieveAllSkiers() {
        List<Skier> skierList = new ArrayList<>();
        when(skierRepository.findAll()).thenReturn(skierList);

        List<Skier> result = skierServices.retrieveAllSkiers();

        verify(skierRepository, times(1)).findAll();
        verifyNoMoreInteractions(skierRepository);
    }

    @Test
    public Skier addSkier(Skier skier) {
        if (skier.getSubscription() == null) {
            // Initialize the Subscription object if it's null
            skier.setSubscription(new Subscription());
        }

        switch (skier.getSubscription().getTypeSub()) {
            case ANNUAL:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusYears(1));
                break;
            case SEMESTRIEL:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusMonths(6));
                break;
            case MONTHLY:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusMonths(1));
                break;
        }

        return skierRepository.save(skier);
    }



    @Test
    public Skier assignSkierToSubscription(Long numSkier, Long numSubscription) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        Subscription subscription = subscriptionRepository.findById(numSubscription).orElse(null);

        if (skier != null && subscription != null) {
            skier.setSubscription(subscription);
            return skierRepository.save(skier);
        } else {
            // Handle the case where either skier or subscription is null
            // You can throw an exception or handle it based on your requirements
            return null; // or throw new SomeException("Skier or Subscription not found");
        }
    }

    @Test
    public Skier addSkierAndAssignToCourse(Skier skier, Long courseId) {
        Course course = courseRepository.getById(courseId); // This line may be causing the NullPointerException

        Set<Registration> registrations = skier.getRegistrations();
        if (registrations == null) {
            registrations = new HashSet<>();
            skier.setRegistrations(registrations);
        }

        Registration registration = new Registration();
        registration.setSkier(skier);
        registration.setCourse(course);
        registrations.add(registration);

        return skierRepository.save(skier);
    }


    @Test //done
    public void testRemoveSkier() {
        skierServices.removeSkier(1L);

        verify(skierRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(skierRepository);
    }

    @Test //done
    public void testRetrieveSkier() {
        when(skierRepository.findById(anyLong())).thenReturn(Optional.of(new Skier()));

        Skier result = skierServices.retrieveSkier(1L);

        verify(skierRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(skierRepository);
    }

    @Test //done
    public void testAssignSkierToPiste() {
        Skier skier = new Skier();
        Piste piste = new Piste();

        when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));
        when(pisteRepository.findById(anyLong())).thenReturn(Optional.of(piste)); // Add this line
        when(skierRepository.save(any(Skier.class))).thenReturn(skier);

        Skier result = skierServices.assignSkierToPiste(1L, 456L);

        verify(skierRepository, times(1)).findById(anyLong());
        verify(pisteRepository, times(1)).findById(anyLong()); // Verify the pisteRepository call
        verify(skierRepository, times(1)).save(any(Skier.class));
        verifyNoMoreInteractions(skierRepository, pisteRepository); // Include pisteRepository in noMoreInteractions
    }


    @Test//done
    public void testRetrieveSkiersBySubscriptionType() {
        List<Skier> skierList = new ArrayList<>();
        when(skierRepository.findBySubscription_TypeSub(any(TypeSubscription.class))).thenReturn(skierList);

        List<Skier> result = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        verify(skierRepository, times(1)).findBySubscription_TypeSub(any(TypeSubscription.class));
        verifyNoMoreInteractions(skierRepository);
    }
}
