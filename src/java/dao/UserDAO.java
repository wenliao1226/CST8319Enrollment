package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (Username, Password, Email, First_Name, Last_Name, Type) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getType());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User authenticateUser(String username, String password) {
        String sql = "SELECT * FROM user WHERE Username = ? AND Password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(int userId, String currentPassword, String newPassword, String firstName, String lastName, String email) {
        String validateSql = "SELECT Password FROM user WHERE User_ID = ?";
        String updateSql = "UPDATE user SET Password = ?, First_Name = ?, Last_Name = ?, Email = ? WHERE User_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement validateStmt = connection.prepareStatement(validateSql);
             PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

            // Step 1: Validate current password
            validateStmt.setInt(1, userId);
            ResultSet resultSet = validateStmt.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("Password");
                if (!storedPassword.equals(currentPassword)) {
                    System.out.println("Current password does not match.");
                    return false; // Password validation failed
                }
            } else {
                System.out.println("User not found.");
                return false; // User does not exist
            }

            // Step 2: Update user details (including password)
            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, firstName);
            updateStmt.setString(3, lastName);
            updateStmt.setString(4, email);
            updateStmt.setInt(5, userId);

            int rowsAffected = updateStmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE User_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("User_ID"),
                resultSet.getString("Username"),
                resultSet.getString("Password"),
                resultSet.getString("Email"),
                resultSet.getString("First_Name"),
                resultSet.getString("Last_Name"),
                null, null, null,
                resultSet.getString("Type")
        );
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE User_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
