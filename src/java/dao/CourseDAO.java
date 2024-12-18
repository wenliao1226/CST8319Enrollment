package dao;

import model.Course;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public int addCourse(Course course) {
        String sql = "INSERT INTO course (Course_Name, Program_ID, Credits, Capacity, Instructor, Schedule, Location, Description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, course.getName());
            statement.setInt(2, course.getProgramId());
            statement.setInt(3, course.getCredits());
            statement.setInt(4, course.getCapacity());
            statement.setString(5, course.getInstructor());
            statement.setString(6, course.getSchedule());
            statement.setString(7, course.getLocation());
            statement.setString(8, course.getDescription());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the auto-incremented Course_ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if the course is not added
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM course WHERE Course_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            System.out.println("Executing query for Course_ID = " + courseId);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Course found in database.");
                return mapCourse(resultSet);
            } else {
                System.out.println("No course found for Course_ID = " + courseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
            resultSet.getString("Description")
        );
    }

    public boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM course WHERE Course_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, courseId);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); // Debugging
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                courses.add(mapCourse(resultSet)); // Reuse mapCourse method
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public List<Course> searchCourses(String courseName, String courseId, String programId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE " +
                     "(? IS NULL OR Course_Name LIKE ?) AND " +
                     "(? IS NULL OR Course_ID = ?) AND " +
                     "(? IS NULL OR Program_ID = ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, courseName);
            statement.setString(2, courseName != null ? "%" + courseName + "%" : null);
            statement.setString(3, courseId);
            statement.setString(4, courseId);
            statement.setString(5, programId);
            statement.setString(6, programId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(mapCourse(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Get courses by Program ID
    public List<Course> getCoursesByProgramId(int programId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE Program_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, programId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                courses.add(mapCourse(resultSet)); // Reuse mapCourse method
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    // Update an existing course
    public boolean updateCourse(Course course) {
        String sql = "UPDATE course SET Course_Name = ?, Program_ID = ?, Credits = ?, Capacity = ?, " +
                     "Instructor = ?, Schedule = ?, Location = ?, Description = ? WHERE Course_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getName());
            statement.setInt(2, course.getProgramId());
            statement.setInt(3, course.getCredits());
            statement.setInt(4, course.getCapacity());
            statement.setString(5, course.getInstructor());
            statement.setString(6, course.getSchedule());
            statement.setString(7, course.getLocation());
            statement.setString(8, course.getDescription());
            statement.setInt(9, course.getCourseID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 // Enroll a student in a course
    public boolean enrollStudent(int studentID, int courseID) {
        String sql = "INSERT INTO enrollments (Student_ID, Course_ID) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setInt(2, courseID);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Drop a student from a course
    public boolean dropStudent(int studentID, int courseID) {
        String sql = "DELETE FROM enrollments WHERE Student_ID = ? AND Course_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setInt(2, courseID);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
