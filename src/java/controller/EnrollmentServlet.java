package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import model.Enrollment;
import service.EnrollmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/enrollment")
public class EnrollmentServlet extends HttpServlet {
    private EnrollmentService enrollmentService = new EnrollmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("enroll".equals(action)) {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Enrollment enrollment = new Enrollment(0, studentId, courseId, new Date(), "Active");
            boolean success = enrollmentService.enrollStudent(enrollment);
            response.getWriter().write(success ? "Enrollment successful" : "Failed to enroll");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("getByStudentId".equals(action)) {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            response.getWriter().write(enrollmentService.getEnrollmentsByStudentId(studentId).toString());
        }
    }
}
