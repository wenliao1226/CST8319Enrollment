package controller;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpSession;

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
    } else if ("logout".equalsIgnoreCase(action)) {
        handleLogout(request, response);
    } else if ("update".equalsIgnoreCase(action)) {
    handleUpdateUser(request, response);
    } else {
            response.getWriter().write("Invalid action specified");
        }
}

private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    request.getSession().invalidate();
    
    response.sendRedirect("login.jsp");
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("userName", user.getUsername()); 
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); 
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userName", user.getUsername());

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
    
    private void handleUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        Date dateOfBirth = null;

        try {
            String dob = request.getParameter("dateOfBirth");
            if (dob != null && !dob.isEmpty()) {
                dateOfBirth = java.sql.Date.valueOf(dob);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        User user = new User(userId, username, password, email, firstName, lastName, address, phoneNumber, dateOfBirth, null);

        boolean success = userService.updateUser(user);

        if (success) {
            
            request.getSession().setAttribute("user", user);
            System.out.println("User updated successfully: " + user);
            response.sendRedirect("profile.jsp");
        } else {
           
            request.setAttribute("error", "Update failed. Please try again.");
            System.out.println("Update failed for user: " + user);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    } catch (Exception e) {
        System.out.println("Error occurred while updating user: " + e.getMessage());
        e.printStackTrace();
        request.setAttribute("error", "An error occurred. Please try again.");
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}

}
