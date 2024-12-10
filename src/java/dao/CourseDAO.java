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

   
    public boolean addCourse(Course course) {
    if (isDuplicateCourse(course.getCourseId(), course.getCourseName())) {
        return false; // 重复记录
    }

    String sql = "INSERT INTO course (Course_Name, Program_ID, Credits, Capacity, Instructor, Schedule, Location, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, course.getCourseName());
        statement.setInt(2, course.getProgramId());
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
        resultSet.getInt("Program_ID"),
        resultSet.getInt("Credits"),
        resultSet.getInt("Capacity"),
        resultSet.getString("Instructor"),
        resultSet.getString("Schedule"),
        resultSet.getString("Location"),
        resultSet.getString("Description"),
        resultSet.getString("Program") // 添加 Program 字段
    );
}
    
    public boolean isDuplicateCourse(int courseId, String courseName) {
    String sql = "SELECT COUNT(*) FROM course WHERE Course_ID = ? OR Course_Name = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, courseId);
        statement.setString(2, courseName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) > 0) {
            return true; // 存在重复记录
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    public List<Course> searchCourses(String courseName, String courseID, String program) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.Course_ID, c.Course_Name, c.Capacity, c.Instructor, c.Location, p.Program_Name " +
                     "FROM course c " +
                     "LEFT JOIN program p ON c.Program_ID = p.Program_ID";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                courses.add(new Course(
                    resultSet.getInt("Course_ID"),
                    resultSet.getString("Course_Name"),
                    0, // Program_ID 如果需要可从数据库结果中获取
                    resultSet.getInt("Capacity"),
                    resultSet.getString("Instructor"),
                    "", // Schedule 如果需要可从数据库结果中获取
                    resultSet.getString("Location"),
                    "", // Description 如果需要可从数据库结果中获取
                    resultSet.getString("Program_Name") // Program_Name
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }




    public boolean deleteCourse(int courseId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
