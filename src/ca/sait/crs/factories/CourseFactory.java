package ca.sait.crs.factories;


import ca.sait.crs.contracts.Course;
import ca.sait.crs.exceptions.CannotCreateCourseException;
import ca.sait.crs.models.OptionalCourse;
import ca.sait.crs.models.RequiredCourse;

/**
 * Creates course instances.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public final class CourseFactory {

    public CourseFactory() {
    }

    /**
     * Builds a Course instance
     * @param code Course code
     * @param name Name of course
     * @param credits Number of credits for course
     * @return RequiredCourse or OptionalCourse instance
     */
    public Course build(String code, String name, int credits) throws CannotCreateCourseException {
        if (!this.validateCode(code)) {
            throw new CannotCreateCourseException("Course code is invalid.");
        }

        if (!this.validateName(name)) {
            throw new CannotCreateCourseException("Course name is invalid.");
        }

        if (!this.validateCredits(credits)) {
            throw new CannotCreateCourseException("Course credits is invalid.");
        }

        if(credits == 0){
            OptionalCourse newCourse = new OptionalCourse(code,name);
            return  newCourse;
        }else{
            RequiredCourse newCourse = new RequiredCourse(code,name,credits);
            return  newCourse;
        }


    }

    /**
     * Checks the course code is valid.
     * @param code Course code
     * @return True if the course code is valid.
     */
    private boolean validateCode(String code) {
        if(code.contains("-")){
            String[] parts = code.split("-");
            String title = parts[0];
            int number = Integer.parseInt(parts[1]);
            if(title.length() == 4 && number < 400){
                return true;
            }
        }
        return false;
    }

    /**
     * Validates course name
     * @param name Course name
     * @return True if course name is valid.
     */
    private boolean validateName(String name) {
        if(name.length() <= 40){
            return true;
        }
        return false;
    }

    /**
     * Validates course credits
     * @param credits Course credits
     * @return True if credits value is valid.
     */
    private boolean validateCredits(int credits) {
        if(credits == 3 || credits == 0){
            return true;
        }
        return false;
    }
}
