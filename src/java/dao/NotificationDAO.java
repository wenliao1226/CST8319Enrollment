/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Notification;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {


    public boolean addNotification(Notification notification) {
        String sql = "INSERT INTO notification (User_ID, Message, Date, Status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notification.getUserId());
            statement.setString(2, notification.getMessage());
            statement.setTimestamp(3, new Timestamp(notification.getDate().getTime()));
            statement.setString(4, notification.getStatus());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 
    public List<Notification> getNotificationsByUserId(int userId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notification WHERE User_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                notifications.add(mapNotification(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

 
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notification";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                notifications.add(mapNotification(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }


    public boolean markNotificationAsRead(int notificationId) {
        String sql = "UPDATE notification SET Status = 'Read' WHERE Notification_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 
    public boolean deleteNotification(int notificationId) {
        String sql = "DELETE FROM notification WHERE Notification_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 
    private Notification mapNotification(ResultSet resultSet) throws SQLException {
        return new Notification(
                resultSet.getInt("Notification_ID"),
                resultSet.getInt("User_ID"),
                resultSet.getString("Message"),
                resultSet.getTimestamp("Date"),
                resultSet.getString("Status")
        );
    }
}

