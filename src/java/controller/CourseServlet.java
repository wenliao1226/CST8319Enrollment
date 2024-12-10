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
    // 打印收到的参数
    System.out.println("=== Received Parameters ===");
    System.out.println("Action: " + request.getParameter("action"));
    System.out.println("Course ID: " + request.getParameter("courseId"));
    System.out.println("Course Name: " + request.getParameter("courseName"));
    System.out.println("Program ID: " + request.getParameter("programId"));
    System.out.println("Capacity: " + request.getParameter("capacity"));
    System.out.println("Instructor: " + request.getParameter("instructor"));
    System.out.println("Location: " + request.getParameter("location"));
    System.out.println("===========================");

    // 继续处理逻辑
    String action = request.getParameter("action");
    if ("add".equals(action)) {
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    String courseName = request.getParameter("courseName");

    // 检查重复记录
    if (courseService.isDuplicateCourse(courseId, courseName)) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Course ID or Name already exists.");
        return;
    }
        try {
            int programId = Integer.parseInt(request.getParameter("programId"));
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String instructor = request.getParameter("instructor");
            String location = request.getParameter("location");

            Course course = new Course(courseId, courseName, programId, capacity, instructor, "", location, "");
            boolean success = courseService.addCourse(course);

            if (success) {
                response.getWriter().write("Course added successfully.");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to add course.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid numerical input: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid input.");
        }
    }
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if ("getAll".equals(action)) {
            List<Course> courses = courseService.getAllCourses();
            System.out.println("Courses fetched in doGet: " + courses.size()); // 调试信息
            response.getWriter().write(new Gson().toJson(courses));
        }
        
        
    }


}
