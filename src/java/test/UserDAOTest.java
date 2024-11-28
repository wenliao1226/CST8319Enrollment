package test;

import dao.UserDAO;
import model.User;

public class UserDAOTest {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Test user registration
        User user = new User(0, "jane_doe", "password123", "jane@example.com", "Jane", "Doe", "123 Street", "1234567890", null, "Student");
        boolean registerResult = userDAO.registerUser(user);
        System.out.println("User registration result: " + registerResult);

        // Test user login
        User loginResult = userDAO.authenticateUser("jane_doe", "password123");
        System.out.println("Logged in user: " + (loginResult != null ? loginResult : "Login failed"));

        // Test getting all users
        System.out.println("All users: ");
        userDAO.getAllUsers().forEach(System.out::println);

        // Test getting a user by ID
        User userById = userDAO.getUserById(1); // Replace with an existing User_ID
        System.out.println("User by ID: " + userById);

        // Test deleting a user
        boolean deleteResult = userDAO.deleteUser(1); // Replace with an existing User_ID
        System.out.println("Delete user result: " + deleteResult);
    }
}
