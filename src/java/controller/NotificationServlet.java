/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Notification;
import service.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/notification")
public class NotificationServlet extends HttpServlet {
    private NotificationService notificationService = new NotificationService();

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("add".equals(action)) {
        String userIdParam = request.getParameter("userId");
        if (userIdParam == null || userIdParam.isEmpty()) {
            response.getWriter().write("Error: userId parameter is missing or invalid.");
            return;
        }

        int userId = Integer.parseInt(userIdParam);
        String message = request.getParameter("message");
        if (message == null || message.isEmpty()) {
            response.getWriter().write("Error: message parameter is missing or empty.");
            return;
        }

        Notification notification = new Notification(0, userId, message, new Date(), "Unread");
        boolean success = notificationService.addNotification(notification);
        response.getWriter().write(success ? "Notification added successfully" : "Failed to add notification");
    }
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("getByUserId".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            response.getWriter().write(notificationService.getNotificationsByUserId(userId).toString());
        }
    }
}
