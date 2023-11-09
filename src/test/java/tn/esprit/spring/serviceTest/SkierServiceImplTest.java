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
 class SkierServiceImplTest {

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
     void testRetrieveAllSkiers() {
        List<Skier> skierList = new ArrayList<>();
        when(skierRepository.findAll()).thenReturn(skierList);

        List<Skier> result = skierServices.retrieveAllSkiers();

        verify(skierRepository, times(1)).findAll();
        verifyNoMoreInteractions(skierRepository);
    }

    @Test
    void addSkier() {
        // Assuming you have Skier and Subscription entities along with repositories
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        skier.setSubscription(subscription);
        skier.getSubscription().setTypeSub(TypeSubscription.ANNUAL); // Set the subscription type

        // Call the method
        skierServices.addSkier(skier);

        // Verify that skierRepository.save is called with the modified skier
        verify(skierRepository, times(1)).save(skier);

        // Add assertions based on your actual business logic
        assertNotNull(skier.getSubscription());
        assertEquals(TypeSubscription.ANNUAL, skier.getSubscription().getTypeSub());
        assertNotNull(skier.getSubscription().getEndDate());

        // You can add more assertions as needed
    }





    @Test
    void assignSkierToSubscription() {
        // Assuming you have Skier and Subscription entities along with repositories
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        Long numSkier = 1L;
        Long numSubscription = 2L;

        when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));
        when(subscriptionRepository.findById(numSubscription)).thenReturn(Optional.of(subscription));

        // Call the method
        skierServices.assignSkierToSubscription(numSkier, numSubscription);

        // Verify that skierRepository.save is called with the modified skier
        verify(skierRepository, times(1)).save(skier);

        // Add assertions based on your actual business logic
        assertNotNull(skier.getSubscription());
        assertEquals(subscription, skier.getSubscription());
    }



    @Test
    void addSkierAndAssignToCourse() {
        // Create a Skier and a Course for testing
        Skier skier = new Skier();
        Course course = new Course();
        Long courseId = 1L;

        when(courseRepository.getById(courseId)).thenReturn(course);

        // Call the method
        skierServices.addSkierAndAssignToCourse(skier, courseId);

        // Verify that skierRepository.save is called with the modified skier
        verify(skierRepository, times(1)).save(skier);

        // Add assertions based on your actual business logic
        assertNotNull(skier.getRegistrations());
        assertFalse(skier.getRegistrations().isEmpty());
        assertEquals(course, skier.getRegistrations().iterator().next().getCourse());
    }




    @Test //done
     void testRemoveSkier() {
        skierServices.removeSkier(1L);

        verify(skierRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(skierRepository);
    }

    @Test //done
     void testRetrieveSkier() {
        when(skierRepository.findById(anyLong())).thenReturn(Optional.of(new Skier()));

        Skier result = skierServices.retrieveSkier(1L);

        verify(skierRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(skierRepository);
    }

    @Test //done
     void testAssignSkierToPiste() {
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
     void testRetrieveSkiersBySubscriptionType() {
        List<Skier> skierList = new ArrayList<>();
        when(skierRepository.findBySubscription_TypeSub(any(TypeSubscription.class))).thenReturn(skierList);

        List<Skier> result = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        verify(skierRepository, times(1)).findBySubscription_TypeSub(any(TypeSubscription.class));
        verifyNoMoreInteractions(skierRepository);
    }
}
