package test;

import model.Notification;
import service.NotificationService;

import java.util.Date;

public class NotificationServiceTest {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        // Test adding a notification
        Notification newNotification = new Notification(0, 1, "This is a test notification", new Date(), "Unread"); // Replace with existing User_ID
        boolean addNotificationResult = notificationService.addNotification(newNotification);
        System.out.println("Add notification result: " + (addNotificationResult ? "Success" : "Failed"));

        // Test fetching notifications by user ID
        System.out.println("Notifications for user:");
        notificationService.getNotificationsByUserId(1).forEach(System.out::println); // Replace with existing User_ID

        // Test marking a notification as read
        boolean markAsReadResult = notificationService.markNotificationAsRead(1); // Replace with existing Notification_ID
        System.out.println("Mark notification as read result: " + (markAsReadResult ? "Success" : "Failed"));

        // Test deleting a notification
        boolean deleteNotificationResult = notificationService.deleteNotification(1); // Replace with existing Notification_ID
        System.out.println("Delete notification result: " + (deleteNotificationResult ? "Success" : "Failed"));
    }
}
