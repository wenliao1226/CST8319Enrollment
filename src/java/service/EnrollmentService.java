package service;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.UserDAO;
import model.Enrollment;

import java.util.List;

public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private UserDAO userDAO = new UserDAO();

    // Enroll a student in a course
    public boolean enrollStudent(Enrollment enrollment) {
        int courseId = enrollment.getCourseId();
        int studentId = enrollment.getStudentId();

        // Validate course existence
        if (courseDAO.getCourseById(courseId) == null) {
            System.out.println("Course does not exist. Cannot enroll student.");
            return false;
        }

        // Validate student existence
        if (userDAO.getUserById(studentId) == null) {
            System.out.println("Student does not exist. Cannot enroll student.");
            return false;
        }

        // Check if the student is already enrolled in the course
        List<Enrollment> existingEnrollments = enrollmentDAO.getEnrollmentsByStudentId(studentId);
        for (Enrollment existingEnrollment : existingEnrollments) {
            if (existingEnrollment.getCourseId() == courseId) {
                System.out.println("Student is already enrolled in the course. Duplicate enrollment is not allowed.");
                return false;
            }
        }

        // Proceed with enrollment
        return enrollmentDAO.enrollStudent(enrollment);
    }

    // Get enrollments by student ID
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollmentDAO.getEnrollmentsByStudentId(studentId);
    }

    // Drop a course
    public boolean dropCourse(int enrollmentId) {
        return enrollmentDAO.dropCourse(enrollmentId);
    }
}
