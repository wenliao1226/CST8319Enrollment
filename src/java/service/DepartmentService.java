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

    // 添加部门
    public boolean addDepartment(Department department) {
        return departmentDAO.addDepartment(department);
    }

    // 获取所有部门
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    // 根据 ID 获取部门信息
    public Department getDepartmentById(int departmentId) {
        return departmentDAO.getDepartmentById(departmentId);
    }

    // 更新部门信息
    public boolean updateDepartment(Department department) {
        return departmentDAO.updateDepartment(department);
    }

    // 删除部门
    public boolean deleteDepartment(int departmentId) {
        return departmentDAO.deleteDepartment(departmentId);
    }
}
