package service;

import dao.ProgramDAO;
import dao.CourseDAO;
import model.Course;
import model.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramService {
    private final ProgramDAO programDAO = new ProgramDAO();
    private final CourseDAO courseDAO = new CourseDAO(); // Create an instance of CourseDAO


    // Add a new program
    public boolean addProgram(Program program) {
        if (program.getName() == null || program.getName().trim().isEmpty()) {
            System.out.println("Invalid program name");
            return false; // Return false if the program name is invalid
        }
        return programDAO.addProgram(program); // Delegate to DAO
    }

    // Get a program by ID
    public Program getProgramById(int programId) {
        if (programId <= 0) {
            System.out.println("Invalid Program ID");
            return null; // Return null if the program ID is invalid
        }
        return programDAO.getProgramById(programId); // Delegate to DAO
    }

    // Get all programs
    public List<Program> getAllPrograms() {
        return programDAO.getAllPrograms(); // Delegate to DAO
    }

 // Delete a program
    public boolean deleteProgram(int programId) {
        if (programId <= 0) {
            System.out.println("Invalid Program ID");
            return false;
        }

        // Check for associated courses
        List<Course> courses = courseDAO.getCoursesByProgramId(programId);
        if (!courses.isEmpty()) {
            System.out.println("Cannot delete program: Courses are associated with it.");
            return false;
        }

        // Proceed with deletion if no courses are associated
        return programDAO.deleteProgram(programId);
    }
    
 // Get courses by Program ID through CourseDAO
    public List<Course> getCoursesByProgramId(int programId) {
        if (programId <= 0) {
            System.out.println("Invalid Program ID");
            return new ArrayList<>(); // Return an empty list for invalid ID
        }
        return courseDAO.getCoursesByProgramId(programId); // Delegate to DAO
    }

}
