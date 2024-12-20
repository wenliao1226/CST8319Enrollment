/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Department;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {


    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO department (Name, Program) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getProgram());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Department getDepartmentById(int departmentId) {
        String sql = "SELECT * FROM department WHERE Department_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapDepartment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                departments.add(mapDepartment(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }


    public boolean updateDepartment(Department department) {
        String sql = "UPDATE department SET Name = ?, Program = ? WHERE Department_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getProgram());
            statement.setInt(3, department.getDepartmentId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public boolean deleteDepartment(int departmentId) {
        String sql = "DELETE FROM department WHERE Department_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    private Department mapDepartment(ResultSet resultSet) throws SQLException {
        return new Department(
                resultSet.getInt("Department_ID"),
                resultSet.getString("Name"),
                resultSet.getString("Program")
        );
    }
}
