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
        // check if user exist
        User existingUser = userDAO.authenticateUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            System.out.println("User already exists, registration failed");
            return false;
        }
        return userDAO.registerUser(user);
    }

    // login
    public User login(String username, String password) {
    User user = userDAO.getUserByUsernameAndPassword(username, password);
    return user; // 如果验证失败，返回 null；成功则返回完整用户对象
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
