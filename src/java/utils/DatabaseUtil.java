package utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wenli
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.ConfigUtil;


public class DatabaseUtil {
    static {
        try {
            // Load the database driver
            Class.forName(ConfigUtil.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // Use the properties to get the connection
        String url = ConfigUtil.getProperty("db.url");
        String username = ConfigUtil.getProperty("db.username");
        String password = ConfigUtil.getProperty("db.password");
        return DriverManager.getConnection(url, username, password);
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

