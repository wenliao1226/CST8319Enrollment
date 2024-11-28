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
            if ("getByName".equalsIgnoreCase(action)) {
                // 根据课程名字获取课程
                String courseName = request.getParameter("courseName");
                if (courseName == null || courseName.trim().isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"message\": \"Course name is required\"}");
                    return;
                }
                List<Course> courses = courseService.getCoursesByName(courseName.trim());
                response.getWriter().write(new Gson().toJson(courses));

            } else if ("getByProgram".equalsIgnoreCase(action)) {
                // 根据 program 获取课程
                String program = request.getParameter("program");
                if (program == null || program.trim().isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"message\": \"Program is required\"}");
                    return;
                }
                List<Course> courses = courseService.getCoursesByProgram(program.trim());
                response.getWriter().write(new Gson().toJson(courses));

            } else {
                // 无效的 action 参数
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"message\": \"Invalid action specified\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"An unexpected error occurred\"}");
            e.printStackTrace();
        }
    }

}