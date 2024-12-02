/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Course;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // 插入新课程
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO course (Course_Name, Department_ID, Credits, Capacity, Instructor, Schedule, Location, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getCourseName());
            statement.setInt(2, course.getDepartmentId());
            statement.setInt(3, course.getCredits());
            statement.setInt(4, course.getCapacity());
            statement.setString(5, course.getInstructor());
            statement.setString(6, course.getSchedule());
            statement.setString(7, course.getLocation());
            statement.setString(8, course.getDescription());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

    // 根据 ID 获取课程
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM course WHERE Course_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapCourse(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Search course by key words
  

    private Course mapCourse(ResultSet resultSet) throws SQLException {
    return new Course(
        resultSet.getInt("Course_ID"),
        resultSet.getString("Course_Name"),
        resultSet.getInt("Department_ID"),
        resultSet.getInt("Credits"),
        resultSet.getInt("Capacity"),
        resultSet.getString("Instructor"),
        resultSet.getString("Schedule"),
        resultSet.getString("Location"),
        resultSet.getString("Description"),
        resultSet.getString("Program") // 添加 Program 字段
    );
}

    public List<Course> searchCourses(String courseName, String courseID, String program) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Course> getAllCourses() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean deleteCourse(int courseId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
