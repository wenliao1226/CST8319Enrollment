package controller;

import model.Course;
import model.Program;
import service.CourseService;
import service.ProgramService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseService courseService = new CourseService();
    private final ProgramService programService = new ProgramService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");

        try {
            if ("add".equalsIgnoreCase(action)) {
                // Adding a Course
                String courseName = request.getParameter("courseName");
                String programIDStr = request.getParameter("programID");
                String creditsStr = request.getParameter("credits");
                String capacityStr = request.getParameter("capacity");
                String instructor = request.getParameter("instructor");
                String schedule = request.getParameter("schedule");
                String location = request.getParameter("location");
                String description = request.getParameter("description");

                int programID = (programIDStr != null && !programIDStr.isEmpty()) ? Integer.parseInt(programIDStr) : 0;
                int credits = (creditsStr != null && !creditsStr.isEmpty()) ? Integer.parseInt(creditsStr) : 0;
                int capacity = (capacityStr != null && !capacityStr.isEmpty()) ? Integer.parseInt(capacityStr) : 0;

                Course course = new Course();
                course.setName(courseName);
                course.setProgramId(programID);
                course.setCredits(credits);
                course.setCapacity(capacity);
                course.setInstructor(instructor);
                course.setSchedule(schedule);
                course.setLocation(location);
                course.setDescription(description);

                int newCourseID = courseService.addCourse(course);
                if (newCourseID > 0) {
                    response.sendRedirect("admin_dashboard.jsp?message=Course+successfully+added");
                } else {
                    response.sendRedirect("admin_dashboard.jsp?message=Failed+to+add+course");
                }

            } else if ("delete".equalsIgnoreCase(action)) {
                // Deleting a Course
                String courseIDStr = request.getParameter("courseID");
                if (courseIDStr != null && !courseIDStr.isEmpty()) {
                    int courseID = Integer.parseInt(courseIDStr);
                    boolean success = courseService.deleteCourse(courseID);
                    if (success) {
                        response.sendRedirect("admin_dashboard.jsp?message=Course+deleted+successfully");
                    } else {
                        response.sendRedirect("admin_dashboard.jsp?message=Failed+to+delete+course");
                    }
                } else {
                    response.sendRedirect("admin_dashboard.jsp?message=No+courseID+provided+for+deletion");
                }

            } else if ("update".equalsIgnoreCase(action)) {
                // Updating a Course
                String courseIDStr = request.getParameter("courseID");
                String courseName = request.getParameter("courseName");
                String capacityStr = request.getParameter("capacity");

                if (courseIDStr != null && !courseIDStr.isEmpty()) {
                    int courseID = Integer.parseInt(courseIDStr);
                    int capacity = (capacityStr != null && !capacityStr.isEmpty()) ? Integer.parseInt(capacityStr) : 0;

                    Course existingCourse = courseService.getCourseById(courseID);
                    if (existingCourse != null) {
                        existingCourse.setName(courseName);
                        existingCourse.setCapacity(capacity);

                        boolean success = courseService.updateCourse(existingCourse);
                        if (success) {
                            response.sendRedirect("admin_dashboard.jsp?message=Course+updated+successfully");
                        } else {
                            response.sendRedirect("admin_dashboard.jsp?message=Failed+to+update+course");
                        }
                    } else {
                        response.sendRedirect("admin_dashboard.jsp?message=Course+not+found");
                    }
                } else {
                    response.sendRedirect("admin_dashboard.jsp?message=No+courseID+provided+for+update");
                }

            } else if ("addProgram".equalsIgnoreCase(action)) {
                // Adding a Program
                String programName = request.getParameter("programName");
                Program program = new Program();
                program.setName(programName);

                boolean success = programService.addProgram(program);
                if (success) {
                    response.sendRedirect("admin_dashboard.jsp?message=Program+added+successfully");
                } else {
                    response.sendRedirect("admin_dashboard.jsp?message=Failed+to+add+program");
                }

            } else {
                response.sendRedirect("admin_dashboard.jsp?message=Invalid+action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?message=An+error+occurred:" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("search".equalsIgnoreCase(action)) {
                String courseName = request.getParameter("courseName");
                String courseID = request.getParameter("courseID");
                String programID = request.getParameter("programID");

                List<Course> courses = courseService.searchCourses(
                    (courseName != null && !courseName.isEmpty()) ? courseName : null,
                    (courseID != null && !courseID.isEmpty()) ? courseID : null,
                    (programID != null && !programID.isEmpty()) ? programID : null
                );

                request.setAttribute("courses", courses);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);

            } else if ("list".equalsIgnoreCase(action) || action == null) {
                // Default: Fetch all courses
                List<Course> courses = courseService.getAllCourses();
                request.setAttribute("courses", courses);
                request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);

            } else {
                response.sendRedirect("dashboard.jsp?message=Invalid+GET+action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?message=An+error+occurred:" + e.getMessage());
        }
    }
}
