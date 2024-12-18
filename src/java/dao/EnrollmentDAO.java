package dao;

import model.Enrollment;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // Insert an enrollment record
    public boolean enrollStudent(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (Student_ID, Course_ID, Enrollment_Date, Status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, enrollment.getStudentId());
            statement.setInt(2, enrollment.getCourseId());
            statement.setDate(3, Date.valueOf(enrollment.getEnrollmentDate())); // Convert LocalDate to java.sql.Date
            statement.setString(4, enrollment.getStatus());

            return statement.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Foreign key constraint failed. Ensure the referenced Course_ID and Student_ID exist.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get enrollments by student ID
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollment WHERE Student_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                enrollments.add(mapEnrollment(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollments;
    }

    // Drop a course
    public boolean dropCourse(int enrollmentId) {
        String sql = "DELETE FROM enrollment WHERE Enrollment_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, enrollmentId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Map ResultSet to Enrollment object
    private Enrollment mapEnrollment(ResultSet resultSet) throws SQLException {
        return new Enrollment(
                resultSet.getInt("Enrollment_ID"),
                resultSet.getInt("Student_ID"),
                resultSet.getInt("Course_ID"),
                resultSet.getDate("Enrollment_Date").toLocalDate(), // Convert java.sql.Date to LocalDate
                resultSet.getString("Status")
        );
    }
}
