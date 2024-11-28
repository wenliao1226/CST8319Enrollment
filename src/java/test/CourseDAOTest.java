package test;

import dao.CourseDAO;
import model.Course;

public class CourseDAOTest {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();

        // Test adding a course
        Course course = new Course(0, "Math 101", 1, 3, 30, "Dr. Smith", "MWF 10-11", "Room 101", "Basic math course",null);
        boolean addCourseResult = courseDAO.addCourse(course);
        System.out.println("Add course result: " + addCourseResult);

        // Test getting all courses
        System.out.println("All courses: ");
        courseDAO.getAllCourses().forEach(System.out::println);

        // Test getting a course by ID
        Course courseById = courseDAO.getCourseById(1); // Replace with an existing Course_ID
        System.out.println("Course by ID: " + courseById);

        // Test deleting a course
        boolean deleteCourseResult = courseDAO.deleteCourse(1); // Replace with an existing Course_ID
        System.out.println("Delete course result: " + deleteCourseResult);
    }
}
