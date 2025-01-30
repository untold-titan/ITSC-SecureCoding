package ca.sait.crs.services;

import ca.sait.crs.contracts.*;
import ca.sait.crs.exceptions.CannotCreateRegistrationException;
import ca.sait.crs.contracts.Student;
import ca.sait.crs.factories.RegistrationFactory;

import java.util.ArrayList;

// TODO: Make this class immutable.

/**
 * Registers student with course.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public class RealRegistrationService implements RegistrationService {
    private ArrayList<Registration> registrations;

    public RealRegistrationService() {
        this.registrations = new ArrayList<>();
    }

    /**
     * Registers student with course
     * @param student Student Student instance
     * @param course Course Course instance
     * @return Registration instance.
     */
    @Override
    public Registration register(Student student, Course course) throws CannotCreateRegistrationException {
        // TODO: Create instance of RegistrationFactory.
        // TODO: Call build() method in RegistrationFactory instance to handle validating parameters and creating new Registration object.
        // Do not catch CannotCreateRegistrationException in this method.
        RegistrationFactory registrationFactory = new RegistrationFactory();

        ca.sait.crs.models.Registration registration = registrationFactory.build(course, student);


        return registration;
    }

    /**
     * Gets registrations.
     * @return All registrations
     */
    @Override
    public ArrayList<Registration> getRegistrations() {
        return this.registrations;
    }
}
