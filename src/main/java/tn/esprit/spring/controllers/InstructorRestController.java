package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.dtos.InstructorDTO;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private final IInstructorServices instructorServices;

    @Operation(description = "Add Instructor")
    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody InstructorDTO instructorRequest) {
        // Convert the InstructorRequest to Instructor entity and proceed with the service method
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setDateOfHire(instructorRequest.getDateOfHire());

        // Add other mappings as needed

        return instructorServices.addInstructor(instructor);
    }

    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping("/addAndAssignToCourse/{numCourse}")
    public Instructor addAndAssignToInstructor(@RequestBody InstructorDTO instructorRequest, @PathVariable("numCourse") Long numCourse) {
        // Convert the InstructorRequest to Instructor entity and proceed with the service method
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setDateOfHire(instructorRequest.getDateOfHire());

        // Add other mappings as needed

        return instructorServices.addInstructorAndAssignToCourse(instructor, numCourse);
    }

    @Operation(description = "Retrieve all Instructors")
    @GetMapping("/all")
    public List<Instructor> getAllInstructors(){
        return instructorServices.retrieveAllInstructors();
    }

    @Operation(description = "Update Instructor ")
    @PutMapping("/update")
    public Instructor updateInstructor(@RequestBody Instructor instructor){
        return instructorServices.updateInstructor(instructor);
    }


    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping("/get/{id-instructor}")
    public Instructor getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }

}
