package tn.esprit.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
class InstructorRepositoryTest {


    @Autowired
    private IInstructorRepository instructorRepository;

    @Test
     void testSaveInstructor() {
        // Create an Instructor
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        // Create some Courses for the Instructor
        Set<Course> courses = new HashSet<>();
        Course course1 = new Course();
        course1.setNumCourse(1L);
        courses.add(course1);
        instructor.setCourses(courses);

        // Save the Instructor to the repository
        Instructor savedInstructor = instructorRepository.save(instructor);

        // Verify that the Instructor is saved
        assertNotNull(savedInstructor.getNumInstructor());
        assertEquals("John", savedInstructor.getFirstName());
        assertEquals("Doe", savedInstructor.getLastName());
        assertTrue(savedInstructor.getCourses().contains(course1));
    }
}
