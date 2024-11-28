/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Department;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String program = request.getParameter("program");
            Department department = new Department(0, name, program);
            boolean success = departmentService.addDepartment(department);
            response.getWriter().write(success ? "Department added successfully" : "Failed to add department");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("getAll".equals(action)) {
            response.getWriter().write(departmentService.getAllDepartments().toString());
        } else if ("getById".equals(action)) {
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));
            response.getWriter().write(departmentService.getDepartmentById(departmentId).toString());
        }
    }
}
