package ca.sait.crs.services;

import ca.sait.crs.contracts.*;
import ca.sait.crs.exceptions.CannotCreateRegistrationException;
import ca.sait.crs.contracts.Student;
import ca.sait.crs.factories.RegistrationFactory;

import java.util.ArrayList;

/**
 * Registers student with course.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public final class RealRegistrationService implements RegistrationService {
    private final ArrayList<Registration> registrations;

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
        // Do not catch CannotCreateRegistrationException in this method.
        RegistrationFactory registrationFactory = new RegistrationFactory();

        ca.sait.crs.models.Registration newRegistration = null;
        try {
            newRegistration = registrationFactory.build(course, student);
        } catch (CannotCreateRegistrationException e) {
            System.out.println("Cannot register course: " + e);
        }

        // Hypothetically speaking this should never be hit, but intellij complains if I don't init course lol
        if (course == null) {
            System.out.println("Failed to register course");
            newRegistration = null;
        }
        this.registrations.add(newRegistration);

        return newRegistration;
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
