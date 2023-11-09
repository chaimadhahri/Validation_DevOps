package tn.esprit.spring.DTO;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO implements Serializable {

    Long numCourse;
    int level;
    TypeCourse typeCourse;
    Support support;
    Float price;
    int timeSlot;

}
