package test;

import dao.DepartmentDAO;
import model.Department;

public class DepartmentDAOTest {
    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();

        // Test adding a department
        Department department = new Department(0, "Computer Science", "CS Program");
        boolean addDepartmentResult = departmentDAO.addDepartment(department);
        System.out.println("Add department result: " + addDepartmentResult);

        // Test getting all departments
        System.out.println("All departments: ");
        departmentDAO.getAllDepartments().forEach(System.out::println);

        // Test getting a department by ID
        Department departmentById = departmentDAO.getDepartmentById(1); // Replace with an existing Department_ID
        System.out.println("Department by ID: " + departmentById);

        // Test deleting a department
        boolean deleteDepartmentResult = departmentDAO.deleteDepartment(1); // Replace with an existing Department_ID
        System.out.println("Delete department result: " + deleteDepartmentResult);
    }
}
