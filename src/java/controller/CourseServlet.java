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
    String message = null;
    String messageType = null;

    try {
        if ("add".equals(action)) {
            // 添加课程逻辑
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

        } else if ("update".equals(action)) {
            // 更新课程逻辑
            int courseId = Integer.parseInt(request.getParameter("courseId")); // 新的 Course ID
            String courseName = request.getParameter("courseName");
            int programId = Integer.parseInt(request.getParameter("programId"));
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String instructor = request.getParameter("instructor");
            String location = request.getParameter("location");

            Course course = new Course(courseId, courseName, programId, capacity, instructor, "", location, "");
            boolean success = courseService.updateCourse(course);

            if (success) {
                message = "Course updated successfully.";
                messageType = "success";
            } else {
                message = "Failed to update course.";
                messageType = "error";
            }

        } else if ("delete".equals(action)) {
            // 删除课程逻辑
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            boolean success = courseService.deleteCourse(courseId);

            if (success) {
                message = "Course deleted successfully.";
                messageType = "success";
            } else {
                message = "Failed to delete course.";
                messageType = "error";
            }
        } else {
            message = "Invalid action.";
            messageType = "error";
        }
    } catch (NumberFormatException e) {
        message = "Invalid input.";
        messageType = "error";
    } catch (Exception e) {
        message = "An unexpected error occurred: " + e.getMessage();
        messageType = "error";
        e.printStackTrace();
    }

    // 设置消息并转发请求
    if (message != null) {
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
    }

    request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.getAllCourses(); 

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(new Gson().toJson(courses));
    }
}

