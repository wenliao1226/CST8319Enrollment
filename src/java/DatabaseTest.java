/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author wenli
 */
import utils.DatabaseUtil;
import java.sql.Connection;

public class DatabaseTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            System.out.println("Connection successful: " + (conn != null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
