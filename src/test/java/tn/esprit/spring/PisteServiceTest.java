package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
 class PisteServicesImplTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteService;

    @Test
    public void testRetrieveAllPistes() {
        // Given
        List<Piste> pisteList = new ArrayList<>();
        pisteList.add(new Piste());
        pisteList.add(new Piste());

        Mockito.when(pisteRepository.findAll()).thenReturn(pisteList);

        // When
        List<Piste> retrievedList = pisteService.retrieveAllPistes();

        // Then
        assertNotNull(retrievedList);
        assertEquals(pisteList.size(), retrievedList.size());
    }

    @Test
    public void testAddPiste() {
        // Given
        Piste pisteToAdd = new Piste();
        pisteToAdd.setNamePiste("TestPiste");
        pisteToAdd.setColor(Color.GREEN);
        pisteToAdd.setLength(100);
        pisteToAdd.setSlope(30);

        Mockito.when(pisteRepository.save(any(Piste.class))).thenReturn(pisteToAdd);

        // When
        Piste addedPiste = pisteService.addPiste(pisteToAdd);

        // Then
        assertNotNull(addedPiste);
        assertEquals("TestPiste", addedPiste.getNamePiste());
        assertEquals(Color.GREEN, addedPiste.getColor());
        assertEquals(100, addedPiste.getLength());
        assertEquals(30, addedPiste.getSlope());
    }

    @Test
    public void testRemovePiste() {
        // Given
        Long numPisteToRemove = 1L;

        // When
        pisteService.removePiste(numPisteToRemove);

        // Then
        Mockito.verify(pisteRepository, times(1)).deleteById(numPisteToRemove);
    }

    @Test
    public void testRetrievePiste() {
        // Given
        Long numPisteToRetrieve = 1L;
        Piste pisteRetrieved = new Piste();
        pisteRetrieved.setNumPiste(numPisteToRetrieve);

        Mockito.when(pisteRepository.findById(numPisteToRetrieve)).thenReturn(Optional.of(pisteRetrieved));

        // When
        Piste retrievedPiste = pisteService.retrievePiste(numPisteToRetrieve);

        // Then
        assertNotNull(retrievedPiste);
        assertEquals(numPisteToRetrieve, retrievedPiste.getNumPiste());
    }
}
