package dao;

import model.Program;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {

    // Add a new program
    public boolean addProgram(Program program) {
        String sql = "INSERT INTO program (Name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, program.getName());
            return statement.executeUpdate() > 0; // Return true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a program by ID
    public Program getProgramById(int programId) {
        String sql = "SELECT * FROM program WHERE Program_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, programId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapProgram(resultSet); // Map the result set to a Program object
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if program not found
    }

    // Get all programs
    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String sql = "SELECT * FROM program";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                programs.add(mapProgram(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs; // Return the list of programs
    }

    // Delete a program by ID
    public boolean deleteProgram(int programId) {
        String sql = "DELETE FROM program WHERE Program_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, programId);
            return statement.executeUpdate() > 0; // Return true if deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to map a result set to a Program object
    private Program mapProgram(ResultSet resultSet) throws SQLException {
        return new Program(
                resultSet.getInt("Program_ID"),
                resultSet.getString("Name")
        );
    }
}
