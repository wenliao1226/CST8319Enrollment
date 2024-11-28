package test;

import model.User;
import service.UserService;

public class UserServiceTest {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Test user registration
        User newUser = new User(0, "john_doe", "password123", "john@example.com", "John", "Doe", null, null, null, "Student");
        boolean registrationResult = userService.registerUser(newUser);
        System.out.println("User registration result: " + (registrationResult ? "Success" : "Failed"));

        // Test user login
        User loggedInUser = userService.login("john_doe", "password123");
        System.out.println("User login result: " + (loggedInUser != null ? loggedInUser : "Login failed"));

        // Test fetching all users
        System.out.println("All users:");
        userService.getAllUsers().forEach(System.out::println);

        // Test fetching a user by ID
        User userById = userService.getUserById(1); // Replace with an existing User_ID
        System.out.println("User by ID: " + userById);

        // Test deleting a user
        boolean deletionResult = userService.deleteUser(1); // Replace with an existing User_ID
        System.out.println("User deletion result: " + (deletionResult ? "Success" : "Failed"));
    }
}
