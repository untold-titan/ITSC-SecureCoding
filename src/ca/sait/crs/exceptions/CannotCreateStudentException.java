package ca.sait.crs.exceptions;

/**
 * Thrown if student can't be created.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public final class CannotCreateStudentException extends Exception {
    public CannotCreateStudentException(String message) {
        super(message);
    }
}
