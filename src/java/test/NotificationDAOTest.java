package test;

import dao.NotificationDAO;
import model.Notification;

import java.util.Date;

public class NotificationDAOTest {
    public static void main(String[] args) {
        NotificationDAO notificationDAO = new NotificationDAO();

        // Test adding a notification
        Notification notification = new Notification(0, 1, "This is a test notification", new Date(), "Unread"); // Replace with existing User_ID
        boolean addNotificationResult = notificationDAO.addNotification(notification);
        System.out.println("Add notification result: " + addNotificationResult);

        // Test getting notifications by user ID
        System.out.println("Notifications for user: ");
        notificationDAO.getNotificationsByUserId(1).forEach(System.out::println); // Replace with existing User_ID

        // Test marking a notification as read
        boolean markAsReadResult = notificationDAO.markNotificationAsRead(1); // Replace with an existing Notification_ID
        System.out.println("Mark notification as read result: " + markAsReadResult);

        // Test deleting a notification
        boolean deleteNotificationResult = notificationDAO.deleteNotification(1); // Replace with an existing Notification_ID
        System.out.println("Delete notification result: " + deleteNotificationResult);
    }
}
