/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wenli
 */
import dao.StudentDAO;
import java.util.List;

public class StudentDAOTest {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Add a student
        dao.addStudent("John Doe", 20);

        // Get all students
        List<String> students = dao.getAllStudents();
        System.out.println("Students: " + students);

        // Delete a student (optional, if you have IDs set up)
        dao.deleteStudent(1); // Replace 1 with an actual ID from your database
    }
}

