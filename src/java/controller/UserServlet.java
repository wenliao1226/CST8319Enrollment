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
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equalsIgnoreCase(action)) {
            handleLogin(request, response);
        } else if ("register".equalsIgnoreCase(action)) {
            handleRegister(request, response);
        } else {
            response.getWriter().write("Invalid action specified");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

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

        User user = userService.login(username, password);

        if (user != null) {
            request.getSession().setAttribute("user", user);

            if ("Student".equalsIgnoreCase(user.getType())) {
                response.sendRedirect("dashboard.jsp");
            } else if ("Admin".equalsIgnoreCase(user.getType())) {
                response.sendRedirect("admin_dashboard.jsp");
            }
        } else {
            // Login failed, set error message and forward to login.jsp
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

            // Debugging Log
            System.out.println("Login failed for username: " + username);
        }
    }


    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");

        // verification email form
        if (!email.endsWith("@collegestudent.com") && !email.endsWith("@collegeadmin.com")) {
            request.setAttribute("error", "Invalid email domain. Use @collegestudent.com or @collegeadmin.com.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // verification password
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordRegex)) {
            request.setAttribute("error", "Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // set user type by email
        String userType = email.endsWith("@collegestudent.com") ? "Student" : "Admin";

        
        User newUser = new User(0, username, password, email, firstName, lastName, null, null, null, userType);

     
        boolean success = userService.registerUser(newUser);

        if (success) {
            
            request.getSession().setAttribute("user", newUser);
            if ("Student".equalsIgnoreCase(userType)) {
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("admin_dashboard.jsp");
            }
        } else {
          
            request.setAttribute("error", "Registration failed! Username or email might already exist.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }


}
