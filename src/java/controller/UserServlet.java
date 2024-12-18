package controller;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        System.out.println("Action received: " + action); // Debug: print action type

        if ("login".equalsIgnoreCase(action)) {
            handleLogin(request, response);
        } else if ("register".equalsIgnoreCase(action)) {
            handleRegister(request, response);
        } else if ("logout".equalsIgnoreCase(action)) {
            handleLogout(request, response);
        } else if ("updatePassword".equalsIgnoreCase(action)) { // Add missing updatePassword action
            handleUpdatePassword(request, response);
        } else {
            response.getWriter().write("Invalid action specified");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        System.out.println("GET request received: Action = " + action);

        if ("getAll".equalsIgnoreCase(action)) {
            response.getWriter().write(userService.getAllUsers().toString());
        } else if ("getById".equalsIgnoreCase(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            response.getWriter().write(userService.getUserById(userId).toString());
        } else {
            response.getWriter().write("Invalid action specified");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Login attempt - Username: " + username + ", Password: " + password);

        User user = userService.login(username, password);

        if (user != null) {
            System.out.println("Login successful for user: " + username);
            request.getSession().setAttribute("user", user);

            if ("Student".equalsIgnoreCase(user.getType())) {
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else if ("Admin".equalsIgnoreCase(user.getType())) {
                response.sendRedirect(request.getContextPath() + "/admin_dashboard.jsp");
            }
        } else {
            System.out.println("Login failed for username: " + username);
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void handleUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmNewPassword = request.getParameter("confirmNewPassword");

            // Check if new passwords match
            if (!newPassword.equals(confirmNewPassword)) {
                request.setAttribute("error", "New passwords do not match.");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }

            boolean success = userService.updatePassword(user.getUserId(), currentPassword, newPassword);

            if (success) {
                request.setAttribute("message", "Password updated successfully.");
            } else {
                request.setAttribute("error", "Failed to update password. Please check your current password.");
            }
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");

        System.out.println("Register attempt - Username: " + username + ", Email: " + email);

        // Email validation
        if (!email.endsWith("@collegestudent.com") && !email.endsWith("@collegeadmin.com")) {
            System.out.println("Invalid email domain: " + email);
            request.setAttribute("error", "Invalid email domain. Use @collegestudent.com or @collegeadmin.com.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Password validation
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        boolean passwordValid = password.matches(passwordRegex);
        System.out.println("Password matches regex: " + passwordValid);

        if (!passwordValid) {
            request.setAttribute("error", "Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Assign user type based on email domain
        String userType = email.endsWith("@collegestudent.com") ? "Student" : "Admin";

        User newUser = new User(0, username, password, email, firstName, lastName, null, null, null, userType);

        boolean success = userService.registerUser(newUser);

        if (success) {
            System.out.println("Registration successful for user: " + username);
            request.getSession().setAttribute("user", newUser);
            if ("Student".equalsIgnoreCase(userType)) {
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin_dashboard.jsp");
            }
        } else {
            System.out.println("Registration failed for user: " + username);
            request.setAttribute("error", "Registration failed! Username or email might already exist.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
