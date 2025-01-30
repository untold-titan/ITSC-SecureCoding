package ca.sait.crs.services;

import ca.sait.crs.contracts.Course;
import ca.sait.crs.exceptions.CannotCreateCourseException;
import ca.sait.crs.factories.CourseFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Make this class immutable.

/**
 * Manages courses
 *
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public final class CourseService {
    /**
     * Path to courses.csv file.
     */
    public static final String COURSES_CSV = "res/courses.csv";

    /**
     * Holds Course instances.
     */
    private final ArrayList<Course> courses;

    /**
     * Initializes CourseService instance
     *
     * @throws FileNotFoundException Thrown if COURSES_CSV file can't be found.
     */
    public CourseService() throws FileNotFoundException {
        this.courses = new ArrayList<>();

        this.load();
    }

    /**
     * Finds course with code
     *
     * @param code Course code
     * @return Course instance or null if not found.
     */
    public Course find(String code) {
        for (Course course : this.courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }

        return null;
    }

    /**
     * Gets copy of courses array list.
     *
     * @return Array list of courses.
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    /**
     * Loads courses from CSV file.
     *
     * @throws FileNotFoundException Thrown if file can't be found.
     */
    private void load() throws FileNotFoundException {
        File file = new File(COURSES_CSV);
        Scanner scanner = new Scanner(file);

        CourseFactory courseFactory = new CourseFactory();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] parts = line.split(",");

            if (parts.length != 3) {
                continue;
            }

            String code = parts[0];
            String name = parts[1];
            int credits = Integer.parseInt(parts[2]);

            Course course = null;

            try {
                if (credits > 0) {
                    course = courseFactory.build(code, name, credits);
                } else {
                    course = courseFactory.build(code, name,0 );
                }
            } catch (CannotCreateCourseException e) {
                System.out.println("Cannot create course");
            }

            // Hypothetically speaking this should never be hit, but intellij complains if I don't init course lol
            if(course == null){
                System.out.println("Failed to create course");
                return;
            }

            this.courses.add(course);
        }

        scanner.close();
    }
}
