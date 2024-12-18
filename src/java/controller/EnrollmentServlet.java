package controller;

import model.Enrollment;
import model.User;
import service.EnrollmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/enrollment")
public class EnrollmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EnrollmentService enrollmentService = new EnrollmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String action = request.getParameter("action");

            if (action == null || action.isEmpty()) {
                response.sendRedirect("dashboard.jsp?message=" + URLEncoder.encode("Missing action parameter", StandardCharsets.UTF_8));
                return;
            }

            switch (action.toLowerCase()) {
                case "enroll":
                    handleEnrollment(request, response);
                    break;
                case "drop":
                    handleDrop(request, response);
                    break;
                default:
                    response.sendRedirect("dashboard.jsp?message=" + URLEncoder.encode("Invalid action", StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?message=" + URLEncoder.encode("An error occurred: " + e.getMessage(), StandardCharsets.UTF_8));
        }
    }

    private void handleEnrollment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));

            Enrollment enrollment = new Enrollment();
            enrollment.setStudentId(studentId);
            enrollment.setCourseId(courseId);
            enrollment.setStatus("Enrolled");
            enrollment.setEnrollmentDate(java.time.LocalDate.now());

            boolean success = enrollmentService.enrollStudent(enrollment);

            if (success) {
                request.setAttribute("message", "Enrollment successful.");
            } else {
                request.setAttribute("error", "Student is already enrolled in the course. Duplicate enrollment is not allowed.");
            }

            fetchEnrolledCourses(request);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input. Please check the student ID or course ID.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }

    private void handleDrop(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int enrollmentId = Integer.parseInt(request.getParameter("enrollmentId"));

            boolean success = enrollmentService.dropCourse(enrollmentId);

            if (success) {
                request.setAttribute("message", "Course dropped successfully.");
            } else {
                request.setAttribute("error", "Failed to drop the course.");
            }

            fetchEnrolledCourses(request);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid enrollment ID.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("getbystudentid".equalsIgnoreCase(action)) {
            fetchEnrolledCourses(request);
            request.getRequestDispatcher("enrolled_courses.jsp").forward(request, response);
        } else {
            response.sendRedirect("dashboard.jsp?message=" + URLEncoder.encode("Invalid action", StandardCharsets.UTF_8));
        }
    }

    private void fetchEnrolledCourses(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                int studentId = user.getUserId();
                List<Enrollment> enrolledCourses = enrollmentService.getEnrollmentsByStudentId(studentId);
                request.setAttribute("enrolledCourses", enrolledCourses);
            } else {
                request.setAttribute("error", "User not logged in. Please log in to view your enrollments.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching enrolled courses.");
        }
    }
}
