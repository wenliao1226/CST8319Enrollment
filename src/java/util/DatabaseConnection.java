package util;

import java.sql.Connection; // 正确的导入
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/enrollment_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Kecacoo174510345";

    public static Connection getConnection() throws SQLException {
        try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");
     
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database connection failed!");
        }
    }
}
