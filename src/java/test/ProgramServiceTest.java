package test;

import model.Program;
import service.ProgramService;

import java.util.List;

public class ProgramServiceTest {
    public static void main(String[] args) {
        ProgramService programService = new ProgramService();

        // Test adding a program
        Program newProgram = new Program(0, "Cyber Security");
        boolean added = programService.addProgram(newProgram);
        System.out.println("Program added: " + added);

        // Test getting all programs
        List<Program> programs = programService.getAllPrograms();
        System.out.println("All programs: ");
        for (Program program : programs) {
            System.out.println(program);
        }

        // Test getting a program by ID
        Program program = programService.getProgramById(1); // Assuming ID 1 exists
        System.out.println("Program with ID 1: " + program);

        // Test deleting a program
        boolean deleted = programService.deleteProgram(3); // Assuming ID 3 exists
        System.out.println("Program with ID 3 deleted: " + deleted);

        // Test getting all programs after deletion
        programs = programService.getAllPrograms();
        System.out.println("Programs after deletion: ");
        for (Program p : programs) {
            System.out.println(p);
        }
    }
}
