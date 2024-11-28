package test;

import model.Department;
import service.DepartmentService;

public class DepartmentServiceTest {
    public static void main(String[] args) {
        DepartmentService departmentService = new DepartmentService();

        // Test adding a department
        Department newDepartment = new Department(0, "Computer Science", "CS Program");
        boolean addDepartmentResult = departmentService.addDepartment(newDepartment);
        System.out.println("Add department result: " + (addDepartmentResult ? "Success" : "Failed"));

        // Test fetching all departments
        System.out.println("All departments:");
        departmentService.getAllDepartments().forEach(System.out::println);

        // Test fetching a department by ID
        Department departmentById = departmentService.getDepartmentById(1); // Replace with existing Department_ID
        System.out.println("Department by ID: " + departmentById);

        // Test deleting a department
        boolean deleteDepartmentResult = departmentService.deleteDepartment(1); // Replace with existing Department_ID
        System.out.println("Delete department result: " + (deleteDepartmentResult ? "Success" : "Failed"));
    }
}
