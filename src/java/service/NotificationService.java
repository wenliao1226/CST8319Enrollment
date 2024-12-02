/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.NotificationDAO;
import model.Notification;

import java.util.List;

public class NotificationService {
    private NotificationDAO notificationDAO = new NotificationDAO();

  
    public boolean addNotification(Notification notification) {
        return notificationDAO.addNotification(notification);
    }


    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationDAO.getNotificationsByUserId(userId);
    }

 
    public List<Notification> getAllNotifications() {
        return notificationDAO.getAllNotifications();
    }

 
    public boolean markNotificationAsRead(int notificationId) {
        return notificationDAO.markNotificationAsRead(notificationId);
    }


    public boolean deleteNotification(int notificationId) {
        return notificationDAO.deleteNotification(notificationId);
    }
}

