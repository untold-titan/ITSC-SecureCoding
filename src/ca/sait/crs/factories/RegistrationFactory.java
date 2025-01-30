package ca.sait.crs.factories;

import ca.sait.crs.CourseRegistrationSystem;
import ca.sait.crs.contracts.Course;
import ca.sait.crs.contracts.Student;
import ca.sait.crs.exceptions.CannotCreateCourseException;
import ca.sait.crs.exceptions.CannotCreateRegistrationException;
import ca.sait.crs.models.OptionalCourse;
import ca.sait.crs.models.Registration;
import ca.sait.crs.models.RequiredCourse;

/**
 * Creates Registration instances.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public class RegistrationFactory {

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

        return null;
    }

    /**
     * Validates a course.
     * @param course Course
     * @return True if course is valid.
     */
    private boolean validateCourse(Course course) {
        // TODO: Add logic to test course is valid.
        if(course.getCode().length() == 3 && Integer.parseInt(course.getCode()) < 400){
            if(course.getName().length()== 4 && course.getName().toUpperCase().equals(course.getName())){
                return true;
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
        // TODO: Add logic to test student is valid.
        if(student.getGpa() > 2.0){
            return true;
        }
        return false;
    }
}
