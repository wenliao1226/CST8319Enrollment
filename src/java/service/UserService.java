/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {
     private final UserDAO userDAO = new UserDAO();

    // rester
    public boolean registerUser(User user) {
    
        List<User> allUsers = userDAO.getAllUsers();
        for (User existingUser : allUsers) {
            if (existingUser.getUsername().equals(user.getUsername()) || existingUser.getEmail().equals(user.getEmail())) {
                System.out.println("User already exists: " + user.getUsername());
                return false;
            }
        }
        return userDAO.registerUser(user);
    }


    // login
    public User login(String username, String password) {
        User user = userDAO.authenticateUser(username, password);
        if (user == null) {
            System.out.println("Login failure:Wrong username or password");
        }
        return user;
    }

    // get all users
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // get users buy ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // delete user
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}
