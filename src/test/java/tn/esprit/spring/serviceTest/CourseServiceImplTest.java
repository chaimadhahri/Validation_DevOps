package tn.esprit.spring.serviceTest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;
import tn.esprit.spring.services.ICourseServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.util.List;

@SpringBootTest
class CourseServicesImplTest {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private CourseServicesImpl courseServices;

    @Test
    void testRetrieveAllCourses() {
        Course course1 = new Course(1L, 1, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 100.0f, 60);
        Course course2 = new Course(2L, 2, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 150.0f, 90);

        courseRepository.save(course1);
        courseRepository.save(course2);

        List<Course> result = courseServices.retrieveAllCourses();

        assertEquals(2, result.size());
        assertEquals(course1.getNumCourse(), result.get(0).getNumCourse());
        assertEquals(course2.getNumCourse(), result.get(1).getNumCourse());
    }

    @Test
    void testAddCourse() {
        Course course = new Course(1L, 1, TypeCourse.INDIVIDUAL, Support.SNOWBOARD, 100.0f, 60);

        Course result = courseServices.addCourse(course);

        assertEquals(course.getNumCourse(), result.getNumCourse());
    }

    @Test
    void testUpdateCourse() {
        Course course = new Course(1L, 1, TypeCourse.INDIVIDUAL, Support.SNOWBOARD, 100.0f, 60);

        courseRepository.save(course);

        Course result = courseServices.updateCourse(course);

        assertEquals(course.getNumCourse(), result.getNumCourse());
    }

    @Test
    void testRetrieveCourse() {
        Course course = new Course(1L, 1, TypeCourse.INDIVIDUAL, Support.SNOWBOARD, 100.0f, 60);

        courseRepository.save(course);

        Course result = courseServices.retrieveCourse(1L);

        assertEquals(course.getNumCourse(), result.getNumCourse());
    }
}
