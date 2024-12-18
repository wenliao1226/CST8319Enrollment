package test;

import dao.ProgramDAO;
import model.Program;

import java.util.List;

public class ProgramDAOTest {
    public static void main(String[] args) {
        ProgramDAO programDAO = new ProgramDAO();

        // Test adding a program
        Program newProgram = new Program(0, "Data Science");
        boolean added = programDAO.addProgram(newProgram);
        System.out.println("Program added: " + added);

        // Test getting all programs
        List<Program> programs = programDAO.getAllPrograms();
        System.out.println("All programs: ");
        for (Program program : programs) {
            System.out.println(program);
        }

        // Test getting a program by ID
        Program program = programDAO.getProgramById(1); // Assuming ID 1 exists
        System.out.println("Program with ID 1: " + program);

        // Test deleting a program by ID
        boolean deleted = programDAO.deleteProgram(2); // Assuming ID 2 exists
        System.out.println("Program with ID 2 deleted: " + deleted);

        // Test getting all programs after deletion
        programs = programDAO.getAllPrograms();
        System.out.println("Programs after deletion: ");
        for (Program p : programs) {
            System.out.println(p);
        }
    }
}
