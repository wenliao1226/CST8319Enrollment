package test;

import dao.EnrollmentDAO;
import dao.UserDAO;
import dao.CourseDAO;
import model.Enrollment;
import model.User;
import model.Course;

import java.util.Date;

public class EnrollmentDAOTest {
    public static void main(String[] args) {
        // Ensure required User and Course exist
        UserDAO userDAO = new UserDAO();
        User user = new User(1, "test_user", "password", "test@example.com", "First", "Last", null, null, null, "Student");
        userDAO.registerUser(user);

        CourseDAO courseDAO = new CourseDAO();
        Course course = new Course(1, "Test Course", 1, 3, 50, "Instructor", "MWF 9-10", "Room 101", "Test course description");
        courseDAO.addCourse(course);

        // Test Enrollment
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        Enrollment enrollment = new Enrollment(0, 1, 1, new Date(), "Active"); // Student_ID=1, Course_ID=1
        boolean enrollResult = enrollmentDAO.enrollStudent(enrollment);
        System.out.println("Student enrollment result: " + enrollResult);

        // Fetch enrollment records
        System.out.println("Enrollment records: ");
        enrollmentDAO.getEnrollmentsByStudentId(1).forEach(System.out::println);

        // Drop course
        boolean dropResult = enrollmentDAO.dropCourse(1); // Replace with valid Enrollment_ID
        System.out.println("Drop course result: " + dropResult);
    }
}
