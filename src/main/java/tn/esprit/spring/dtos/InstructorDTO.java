package tn.esprit.spring.dtos;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.dtos.CourseDTO;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
public class InstructorDTO {
    private Long numInstructor;
    private String firstName;
    private String lastName;
    private LocalDate dateOfHire;
    private Set<CourseDTO> courses;

    // Constructors, getters, and setters

    public InstructorDTO() {
    }

    public InstructorDTO(Long numInstructor, String firstName, String lastName, LocalDate dateOfHire, Set<CourseDTO> courses) {
        this.numInstructor = numInstructor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfHire = dateOfHire;
        this.courses = courses;
    }
}
