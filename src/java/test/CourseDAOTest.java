package test;

import dao.CourseDAO;
import model.Course;

public class CourseDAOTest {

    public static void main(String[] args) {
        CourseDAOTest tester = new CourseDAOTest();

        tester.testAddCourse();
        tester.testDeleteCourse();
    }

    public void testAddCourse() {
        CourseDAO courseDAO = new CourseDAO();
        Course course = new Course(0, "Test Course", 1, 4, 30, "Prof. John", "MWF 10-11", "Room 101", "Testing add method");

        int generatedCourseId = courseDAO.addCourse(course);
        if (generatedCourseId != -1) {
            System.out.println("Test Add Course: PASSED (Generated Course ID: " + generatedCourseId + ")");
        } else {
            System.out.println("Test Add Course: FAILED");
        }
    }

    public void testDeleteCourse() {
        CourseDAO courseDAO = new CourseDAO();

        // Insert a course and get its ID
        Course course = new Course(0, "Test Course to Delete", 1, 3, 20, "Prof. Delete", "MWF 11-12", "Room 104", "Test Deletion");
        int generatedCourseId = courseDAO.addCourse(course);

        if (generatedCourseId == -1) {
            System.out.println("Test Delete Course: FAILED (Course not added)");
            return;
        }

        // Use the generated ID for deletion
        boolean result = courseDAO.deleteCourse(generatedCourseId);
        System.out.println("Test Delete Course: " + (result ? "PASSED" : "FAILED"));

        // Verify the course has been deleted
        Course deletedCourse = courseDAO.getCourseById(generatedCourseId);
        if (deletedCourse == null) {
            System.out.println("Verification: Course successfully deleted.");
        } else {
            System.out.println("Verification: Course still exists.");
        }
    }
}
