package tn.esprit.spring.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class CourseServiceMockTest {
    @Mock
    ICourseRepository courseRepository;
    @InjectMocks
    CourseServicesImpl courseServices;
    @Test
    void testRetrieveAllCourses() {
        Course course1 = new Course(1L, 1, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 100.0f, 60);
        Course course2 = new Course(2L, 2, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 150.0f, 90);
        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseServices.retrieveAllCourses();

        assertEquals(2, result.size());
        assertEquals(course1, result.get(0));
        assertEquals(course2, result.get(1));
    }
    @Test
    void testAddCourse() {
        Course course = new Course(1L, 1, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 100.0f, 60);

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course result = courseServices.addCourse(course);

        assertEquals(course, result);
    }

    @Test
    void testUpdateCourse() {
        Course course = new Course(1L, 1, TypeCourse.INDIVIDUAL, Support.SNOWBOARD, 100.0f, 60);

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course result = courseServices.updateCourse(course);

        assertEquals(course, result);
    }
    @Test
    void testRetrieveCourse() {
        Course course = new Course(1L, 1, TypeCourse.INDIVIDUAL, Support.SKI, 100.0f, 60);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course result = courseServices.retrieveCourse(1L);

        assertEquals(course, result);
    }
}
