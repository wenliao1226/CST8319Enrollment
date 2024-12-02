/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DepartmentDAO;
import model.Department;

import java.util.List;

public class DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAO();

  
    public boolean addDepartment(Department department) {
        return departmentDAO.addDepartment(department);
    }

 
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }


    public Department getDepartmentById(int departmentId) {
        return departmentDAO.getDepartmentById(departmentId);
    }

  
    public boolean updateDepartment(Department department) {
        return departmentDAO.updateDepartment(department);
    }

 
    public boolean deleteDepartment(int departmentId) {
        return departmentDAO.deleteDepartment(departmentId);
    }
}
