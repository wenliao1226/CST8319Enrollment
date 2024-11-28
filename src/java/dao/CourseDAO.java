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
    
        // 根据课程名字获取课程
    public List<Course> getCoursesByName(String courseName) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, d.Program FROM course c " +
                     "LEFT JOIN department d ON c.Department_ID = d.Department_ID " +
                     "WHERE c.Course_Name LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + courseName + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(mapCourse(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    // 根据 program 获取课程
    public List<Course> getCoursesByProgram(String program) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, d.Program FROM course c " +
                     "LEFT JOIN department d ON c.Department_ID = d.Department_ID " +
                     "WHERE d.Program LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + program + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(mapCourse(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    public List<Course> searchCourses(String courseName, String courseID, String program) {
    List<Course> courses = new ArrayList<>();
    StringBuilder sql = new StringBuilder(
        "SELECT c.Course_ID, c.Course_Name, c.Credits, c.Capacity, c.Instructor, c.Schedule, c.Location, c.Description, d.Program " +
        "FROM course c " +
        "LEFT JOIN department d ON c.Department_ID = d.Department_ID " +
        "WHERE 1=1"
    );

    if (courseName != null && !courseName.isEmpty()) {
        sql.append(" AND c.Course_Name LIKE ?");
    }
    if (courseID != null && !courseID.isEmpty()) {
        sql.append(" AND c.Course_ID LIKE ?");
    }
    if (program != null && !program.isEmpty()) {
        sql.append(" AND d.Program LIKE ?");
    }

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql.toString())) {

        int index = 1;
        if (courseName != null && !courseName.isEmpty()) {
            statement.setString(index++, "%" + courseName + "%");
        }
        if (courseID != null && !courseID.isEmpty()) {
            statement.setString(index++, "%" + courseID + "%");
        }
        if (program != null && !program.isEmpty()) {
            statement.setString(index++, "%" + program + "%");
        }

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            courses.add(new Course(
                resultSet.getInt("Course_ID"),
                resultSet.getString("Course_Name"),
                resultSet.getInt("Credits"),
                resultSet.getInt("Capacity"),
                resultSet.getString("Instructor"),
                resultSet.getString("Schedule"),
                resultSet.getString("Location"),
                resultSet.getString("Description"),
                resultSet.getString("Program")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return courses;
}




    // 获取所有课程
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                courses.add(mapCourse(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // 删除课程
    public boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM course WHERE Course_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
        resultSet.getString("Program") 
    );
}


}
