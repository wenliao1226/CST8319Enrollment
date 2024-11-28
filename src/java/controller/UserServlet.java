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


// 登录处理
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password); // 从服务层验证登录

        if (user != null) {
            // 登录成功，存储用户信息到 session
            request.getSession().setAttribute("user", user);

            // 根据用户类型重定向到对应的 dashboard
            if ("Student".equalsIgnoreCase(user.getType())) {
                response.sendRedirect("dashboard.jsp");
            } else if ("Admin".equalsIgnoreCase(user.getType())) {
                response.sendRedirect("admin_dashboard.jsp");
            } else {
                response.getWriter().write("User type not recognized");
            }
        } else {
            // 登录失败
            response.getWriter().write("Invalid username or password");
        }
    }

        private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // 根据邮箱格式自动设置用户类型
        String type = email.endsWith("@student.com") ? "Student" : email.endsWith("@admin.com") ? "Admin" : "Unknown";

        if ("Unknown".equals(type)) {
            response.getWriter().write("Invalid email domain. Please use a valid student or admin email.");
            return;
        }

        User newUser = new User(0, username, password, email, firstName, lastName, null, null, null, type);

        boolean success = userService.registerUser(newUser);

        if (success) {
            response.sendRedirect("login.jsp?message=Registration successful. Please login.");
        } else {
            response.getWriter().write("Registration failed: Username may already exist.");
        }
    }


}
