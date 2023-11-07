package tn.esprit.spring;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;


@SpringBootTest
 class InstructorTest {

    @Test
    void testInstructorProperties() {
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

        // Verify Instructor properties
        assertEquals("John", instructor.getFirstName());
        assertEquals("Doe", instructor.getLastName());
        assertEquals(LocalDate.now(), instructor.getDateOfHire());
        assertEquals(1, instructor.getCourses().size());
    }
}
