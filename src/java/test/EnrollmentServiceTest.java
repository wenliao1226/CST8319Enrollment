package test;

import model.Enrollment;
import service.EnrollmentService;

import java.util.Date;

public class EnrollmentServiceTest {
    public static void main(String[] args) {
        EnrollmentService enrollmentService = new EnrollmentService();

        // Test case: Enroll a student in a course
        Enrollment enrollment = new Enrollment(0, 1, 1, new Date(), "Active"); // Replace with valid Student_ID and Course_ID
        boolean enrollResult = enrollmentService.enrollStudent(enrollment);
        System.out.println("Student enrollment result: " + (enrollResult ? "Success" : "Failed"));

        // Test case: Get enrollments by student ID
        System.out.println("Enrollments for student:");
        enrollmentService.getEnrollmentsByStudentId(1).forEach(System.out::println); // Replace with valid Student_ID

        // Test case: Drop a course
        boolean dropResult = enrollmentService.dropCourse(1); // Replace with valid Enrollment_ID
        System.out.println("Drop course result: " + (dropResult ? "Success" : "Failed"));
    }
}
