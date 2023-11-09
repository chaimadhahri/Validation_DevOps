package tn.esprit.spring.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SkierDTO {
    Long numSkier;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String city;

}
