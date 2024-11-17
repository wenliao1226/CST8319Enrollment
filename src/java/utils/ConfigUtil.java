/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author wenli
 */
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config/dbconfig.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find dbconfig.properties");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static void main(String[] args) {
    System.out.println(ConfigUtil.getProperty("db.url"));
    System.out.println(ConfigUtil.getProperty("db.username"));
    System.out.println(ConfigUtil.getProperty("db.password"));
}

}

