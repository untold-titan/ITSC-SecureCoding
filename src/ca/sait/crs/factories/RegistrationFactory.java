package ca.sait.crs.factories;


import ca.sait.crs.contracts.Course;
import ca.sait.crs.contracts.Student;

import ca.sait.crs.exceptions.CannotCreateRegistrationException;

import ca.sait.crs.models.Registration;


/**
 * Creates Registration instances.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public final class RegistrationFactory {

    public RegistrationFactory() {
    }

    /**
     * Builds a Registration instance.
     * @param course Course
     * @param student Student
     * @return Registration instance
     * @throws CannotCreateRegistrationException Thrown if parameters are invalid.
     */
    public Registration build(Course course, Student student) throws CannotCreateRegistrationException {
        // Do not check if students is eligible for registration here.

        if (!this.validateCourse(course)) {
            throw new CannotCreateRegistrationException("Course is invalid.");
        }

        if (!this.validateStudent(student)) {
            throw new CannotCreateRegistrationException("Student is invalid.");
        }

        Registration newRegistration = new Registration(course,student);
        return newRegistration;
    }

    /**
     * Validates a course.
     * @param course Course
     * @return True if course is valid.
     */
    private boolean validateCourse(Course course) {
        if(course.getCode().contains("-")){
            String[] parts = course.getCode().split("-");
            String title = parts[0];
            int number = Integer.parseInt(parts[1]);
            if(title.length() == 4 && number < 400){
                if(course.getName().length() <= 40){
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Validates a student.
     * @param student Student
     * @return True if student is valid.
     */
    private boolean validateStudent(Student student) {
        if(student.getGpa() > 2.0){
            return true;
        }
        return false;
    }
}
