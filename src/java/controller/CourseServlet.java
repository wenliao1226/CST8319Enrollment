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
        String message = null;
        String messageType = null;
        try {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String courseName = request.getParameter("courseName");
            int programId = Integer.parseInt(request.getParameter("programId"));
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String instructor = request.getParameter("instructor");
            String location = request.getParameter("location");

            Course course = new Course(courseId, courseName, programId, capacity, instructor, "", location, "");
            boolean success = courseService.addCourse(course);

            if (success) {
                message = "Course added successfully.";
                messageType = "success";
            } else {
                message = "Failed to add course.";
                messageType = "error";
            }
        } catch (NumberFormatException e) {
            message = "Invalid input.";
            messageType = "error";
        }

        // 仅在有消息需要显示时设置请求属性
        if (message != null) {
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
        }

        // 转发请求回到当前页面
        request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
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
