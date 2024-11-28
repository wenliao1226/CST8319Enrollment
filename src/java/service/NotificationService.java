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

    // 添加通知
    public boolean addNotification(Notification notification) {
        return notificationDAO.addNotification(notification);
    }

    // 获取用户的所有通知
    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationDAO.getNotificationsByUserId(userId);
    }

    // 获取所有通知
    public List<Notification> getAllNotifications() {
        return notificationDAO.getAllNotifications();
    }

    // 标记通知为已读
    public boolean markNotificationAsRead(int notificationId) {
        return notificationDAO.markNotificationAsRead(notificationId);
    }

    // 删除通知
    public boolean deleteNotification(int notificationId) {
        return notificationDAO.deleteNotification(notificationId);
    }
}

