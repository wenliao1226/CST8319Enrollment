/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Course;
import service.CourseService;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    private CourseService courseService = new CourseService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String name = request.getParameter("courseName");
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));
            int credits = Integer.parseInt(request.getParameter("credits"));
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String instructor = request.getParameter("instructor");
            String schedule = request.getParameter("schedule");
            String location = request.getParameter("location");
            String description = request.getParameter("description");

            Course course = new Course(0, name, departmentId, credits, capacity, instructor, schedule, location, description, null);

            boolean success = courseService.addCourse(course);
            response.getWriter().write(success ? "Course added successfully" : "Failed to add course");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if ("getAll".equalsIgnoreCase(action)) {
                // get all course
                List<Course> courses = courseService.getAllCourses();
                response.getWriter().write(new Gson().toJson(courses));

            } else if ("getById".equalsIgnoreCase(action)) {
                // get course by id
                String courseIdParam = request.getParameter("courseId");

                if (courseIdParam == null || courseIdParam.trim().isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"message\": \"Course ID is required\"}");
                    return;
                }

                int courseId = Integer.parseInt(courseIdParam);
                Course course = courseService.getCourseById(courseId);

                if (course != null) {
                    response.getWriter().write(new Gson().toJson(course));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"message\": \"Course not found\"}");
                }

            } else if ("search".equalsIgnoreCase(action)) {
                // serch course
                String courseName = request.getParameter("courseName");
                String courseID = request.getParameter("courseID");
                String program = request.getParameter("program");

                List<Course> courses = courseService.searchCourses(
                    courseName != null ? courseName.trim() : null,
                    courseID != null ? courseID.trim() : null,
                    program != null ? program.trim() : null
                );

                if (!courses.isEmpty()) {
                    response.getWriter().write(new Gson().toJson(courses));
                } else {
                    response.getWriter().write("[]");
                }

            } else {
                // Invalid action parameter
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"message\": \"Invalid action specified\"}");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"Invalid numerical input\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"An unexpected error occurred\"}");
            e.printStackTrace();
        }
    }


}