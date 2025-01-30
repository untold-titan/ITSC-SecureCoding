package ca.sait.crs.factories;

import ca.sait.crs.contracts.Student;
import ca.sait.crs.exceptions.CannotCreateStudentException;

/**
 * Creates student instances.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public class StudentFactory {
    public StudentFactory() {

    }

    /**
     * Builds a Student instance
     * @param name Name of student
     * @param gpa Student's GPA
     * @return Student instance
     */
    public Student build(String name, double gpa) throws CannotCreateStudentException {
        if (!this.validateName(name))
            throw new CannotCreateStudentException("Student name is invalid.");

        if (!this.validateGpa(gpa))
            throw new CannotCreateStudentException("Student GPA is invalid.");


        ca.sait.crs.models.Student newStudent = new ca.sait.crs.models.Student(name,gpa);
        return newStudent;
    }

    /**
     * Validates student name
     * @param name
     * @return True if name is valid
     */
    private boolean validateName(String name) {
        // Not sure what criteria the name needs...
        // Assuming its first and last
        return name.contains(" ");
    }

    /**
     * Validates student GPA
     * @param gpa
     * @return True if GPA is valid
     */
    private boolean validateGpa(double gpa) {
        return !(gpa > 4) && !(gpa < 0);
    }
}
