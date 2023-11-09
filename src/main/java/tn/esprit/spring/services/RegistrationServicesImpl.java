package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImpl implements  IRegistrationServices{

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;


    @Override
    public Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        Course course = courseRepository.findById(numCourse).orElse(null);

        if (registration != null) {
            registration.setCourse(course);
            return registrationRepository.save(registration);
        } else {
            // Handle the case where registration is null
            // You can throw an exception or handle it based on your requirements
            return null; // or throw new SomeException("Registration not found");
        }
    }


    @Transactional
    @Override
    public Registration addRegistrationAndAssignToSkierAndCourse(Registration registration, Long numSkieur, Long numCours) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Course course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null) {
            return null;
        }

        if (isSkierAlreadyRegistered(registration.getNumWeek(), skier, course)) {
            log.info("Sorry, you're already registered for this course in week: " + registration.getNumWeek());
            return null;
        }

        int ageSkieur = calculateAge(skier.getDateOfBirth());
        log.info("Age " + ageSkieur);

        switch (course.getTypeCourse()) {
            case INDIVIDUAL:
                log.info("Add without tests");
                return assignRegistration(registration, skier, course);

            case COLLECTIVE_CHILDREN:
                return handleCollectiveChildren(ageSkieur, registration, skier, course);

            default:
                return handleCollectiveAdult(ageSkieur, registration, skier, course);
        }
    }

    private boolean isSkierAlreadyRegistered(int numWeek, Skier skier, Course course) {
        return registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(numWeek, skier.getNumSkier(), course.getNumCourse()) >= 1;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private Registration handleCollectiveChildren(int ageSkieur, Registration registration, Skier skier, Course course) {
        if (ageSkieur < 16) {
            return handleCourseRegistration(registration, skier, course, 6);
        } else {
            log.info("Sorry, your age doesn't allow you to register for this course! \n Try to Register for a Collective Adult Course...");
            return null;
        }
    }

    private Registration handleCollectiveAdult(int ageSkieur, Registration registration, Skier skier, Course course) {
        if (ageSkieur >= 16) {
            return handleCourseRegistration(registration, skier, course, 6);
        } else {
            log.info("Sorry, your age doesn't allow you to register for this course! \n Try to Register for a Collective Child Course...");
            return null;
        }
    }

    private Registration handleCourseRegistration(Registration registration, Skier skier, Course course, int maxRegistrations) {
        if (registrationRepository.countByCourseAndNumWeek(course, registration.getNumWeek()) < maxRegistrations) {
            log.info("Course successfully added!");
            return assignRegistration(registration, skier, course);
        } else {
            log.info("Full Course! Please choose another week to register!");
            return null;
        }
    }

    private Registration assignRegistration (Registration registration, Skier skier, Course course){
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }

}
