/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Program;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {
    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String query = "SELECT Program_ID, Program_Name FROM program"; // 确保字段名称与数据库一致
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                programs.add(new Program(
                    rs.getInt("Program_ID"),      // 数据库中的列名为Program_ID
                    rs.getString("Program_Name") // 数据库中的列名为Program_Name
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }
}
