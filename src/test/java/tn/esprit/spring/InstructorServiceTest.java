package tn.esprit.spring;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class InstructorServiceTest {
    @InjectMocks
    private InstructorServicesImpl instructorService;

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddInstructor() {
        Instructor instructor = new Instructor();
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor result = instructorService.addInstructor(instructor);

        assertNotNull(result);
        verify(instructorRepository, times(1)).save(instructor);
    }

    @Test
    public void testRetrieveAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        when(instructorRepository.findAll()).thenReturn(instructors);

        List<Instructor> result = instructorService.retrieveAllInstructors();

        assertEquals(instructors, result);
        verify(instructorRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateInstructor() {
        Instructor instructor = new Instructor();
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor result = instructorService.updateInstructor(instructor);

        assertNotNull(result);
        verify(instructorRepository, times(1)).save(instructor);
    }

    @Test
    public void testRetrieveInstructor() {
        Long numInstructor = 1L;
        Instructor instructor = new Instructor();
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));

        Instructor result = instructorService.retrieveInstructor(numInstructor);

        assertNotNull(result);
        verify(instructorRepository, times(1)).findById(numInstructor);
    }

    @Test
    public void testAddInstructorAndAssignToCourse() {
        Instructor instructor = new Instructor();
        Long numCourse = 1L;
        Course course = new Course();
        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor result = instructorService.addInstructorAndAssignToCourse(instructor, numCourse);

        assertNotNull(result);
        verify(courseRepository, times(1)).findById(numCourse);
        verify(instructorRepository, times(1)).save(instructor);
    }
}
