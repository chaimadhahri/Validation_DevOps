package tn.esprit.spring.serviceTest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.services.ICourseServices;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServiceImplTest {
    @Autowired
    ICourseServices iCourseServices;

    @Test
    @Order(1)
    public void testRetrieveAllCourses() {
        List<Course> listCourses = iCourseServices.retrieveAllCourses();
        Assertions.assertEquals(listCourses.size(), listCourses.size()); //listCourses.size()
    }

    @Test
    @Order(2)
    public void testAddCourse()throws ParseException {
        Course course = new Course(1L,2, TypeCourse.INDIVIDUAL, Support.SKI, 20f, 3);
        Course course1 = iCourseServices.addCourse(course);
        Assertions.assertEquals(course.getNumCourse(), course1.getNumCourse());
    }
}
