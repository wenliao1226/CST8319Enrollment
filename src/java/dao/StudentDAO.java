/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author wenli
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseUtil;

public class StudentDAO {
    //Retrieve All Students
    public List<String> getAllStudents() {
    List<String> students = new ArrayList<>();
    String sql = "SELECT * FROM students";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            students.add(rs.getString("name")); // Change column name as per your DB schema
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return students;
}
    //Add a Student
    public void addStudent(String name, int age) {
    String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    //Delete a Student
    public void deleteStudent(int id) {
    String sql = "DELETE FROM students WHERE id = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    //Update a Student (Optional)
    public void updateStudent(int id, String newName, int newAge) {
    String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, newName);
        stmt.setInt(2, newAge);
        stmt.setInt(3, id);
        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}




    
}
