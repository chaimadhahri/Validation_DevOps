package tn.esprit.spring.dtos;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;

import java.util.List;

@Getter
@Setter
public class CourseDTO {
    private int level;
    private Long numCourse;

    private TypeCourse typeCourse;
    private Support support;
    private Float price;
    private int timeSlot;
    private List<CourseDTO> courses;
}
