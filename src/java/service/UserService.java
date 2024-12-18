package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    // Register User
    public boolean registerUser(User user) {
        System.out.println("Starting user registration...");
        List<User> allUsers = userDAO.getAllUsers();

        System.out.println("Checking for existing users...");
        for (User existingUser : allUsers) {
            System.out.println("Existing User: " + existingUser.getUsername() + ", Email: " + existingUser.getEmail());
            if (existingUser.getUsername().equals(user.getUsername()) || existingUser.getEmail().equals(user.getEmail())) {
                System.out.println("Conflict found with user: " + existingUser.getUsername());
                return false;
            }
        }

        System.out.println("No conflicts found. Attempting to register user: " + user.getUsername());
        boolean success = userDAO.registerUser(user);

        if (success) {
            System.out.println("User successfully registered: " + user.getUsername());
        } else {
            System.err.println("User registration failed: " + user.getUsername());
        }
        return success;
    }

    // Login
    public User login(String username, String password) {
        User user = userDAO.authenticateUser(username, password);
        if (user == null) {
            System.out.println("Login failure: Wrong username or password");
        }
        return user;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Get user by ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // Delete user
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    // Update user password
    public boolean updatePassword(int userId, String currentPassword, String newPassword) {
        User user = getUserById(userId);

        if (user != null && user.getPassword().equals(currentPassword)) {
            // Pass required parameters to DAO method
            return userDAO.updateUser(userId, currentPassword, newPassword, 
                                      user.getFirstName(), user.getLastName(), user.getEmail());
        }

        System.out.println("Password update failed: Invalid current password for user ID " + userId);
        return false;
    }
}
