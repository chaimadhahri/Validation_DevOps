package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PisteServiceTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServiceImpl pisteService;

    @Test
    public void testAddPiste() {
        Piste piste = new Piste();
        piste.setNamePiste("TestPiste");
        piste.setColor(Color.GREEN);
        piste.setLength(100);
        piste.setSlope(30);

        Mockito.when(pisteRepository.save(Mockito.any(Piste.class))).thenReturn(piste);

        Piste addedPiste = pisteService.addPiste(piste);

        assertNotNull(addedPiste.getNumPiste());
        assertEquals("TestPiste", addedPiste.getNamePiste());
        assertEquals(Color.GREEN, addedPiste.getColor());
        assertEquals(100, addedPiste.getLength());
        assertEquals(30, addedPiste.getSlope());
    }

    @Test
    public void testRetrievePiste() {
        Long numPiste = 1L;
        Piste piste = new Piste();
        piste.setNumPiste(numPiste);
        piste.setNamePiste("TestPiste");
        piste.setColor(Color.GREEN);
        piste.setLength(100);
        piste.setSlope(30);

        Mockito.when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        Piste retrievedPiste = pisteService.retrievePiste(numPiste);

        assertNotNull(retrievedPiste);
        assertEquals(numPiste, retrievedPiste.getNumPiste());
    }

    @Test
    public void testRetrieveAllPistes() {
        // Assuming you have a list of pistes in your database
        List<Piste> pisteList = new ArrayList<>();
        pisteList.add(new Piste());
        pisteList.add(new Piste());

        Mockito.when(pisteRepository.findAll()).thenReturn(pisteList);

        List<Piste> retrievedList = pisteService.retrieveAllPistes();

        assertNotNull(retrievedList);
        assertEquals(pisteList.size(), retrievedList.size());
    }

}
