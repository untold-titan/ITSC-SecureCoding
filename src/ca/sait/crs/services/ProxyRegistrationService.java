package ca.sait.crs.services;


import ca.sait.crs.contracts.Course;
import ca.sait.crs.contracts.Registration;
import ca.sait.crs.contracts.RegistrationService;
import ca.sait.crs.contracts.Student;
import ca.sait.crs.exceptions.CannotCreateRegistrationException;

import java.util.ArrayList;

// TODO: Define the ProxyRegistrationService
// TODO: Implement the RegistrationService interface
// TODO: Check student can be registered before passing to RealRegistrationService
// TODO: Make this class immutable.
public final class ProxyRegistrationService implements RegistrationService {

    RealRegistrationService registrations = new RealRegistrationService();
    @Override
    public Registration register(Student student, Course course) throws CannotCreateRegistrationException {
        if (student.getGpa() < 2) {
            throw new CannotCreateRegistrationException("Your GPA is less than two. You are not eligible to register.");
        }

        return registrations.register(student, course);
    }

    @Override
    public ArrayList<Registration> getRegistrations() {
        return registrations.getRegistrations();
    }
}