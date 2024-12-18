package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Program;
import service.ProgramService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/program")
public class ProgramServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ProgramService programService = new ProgramService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            // Parse JSON payload
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }
            String requestBody = sb.toString();
            System.out.println("Received JSON payload: " + requestBody);

            // Parse JSON
            JsonObject json = JsonParser.parseString(requestBody).getAsJsonObject();
            String action = json.has("action") ? json.get("action").getAsString() : null;

            System.out.println("Received POST action: " + action);

            if (action == null || action.isEmpty()) {
                response.getWriter().write("{\"message\":\"Error: Missing action parameter in POST request.\"}");
                return;
            }

            if ("add".equalsIgnoreCase(action)) {
                // Extract name parameter
                String name = json.has("name") ? json.get("name").getAsString() : null;

                if (name == null || name.trim().isEmpty()) {
                    response.getWriter().write("{\"message\":\"Error: Program name is required.\"}");
                    return;
                }

                System.out.println("Adding Program: " + name);

                // Add program
                Program program = new Program(0, name);
                boolean added = programService.addProgram(program);

                if (added) {
                    response.getWriter().write("{\"message\":\"Program added successfully.\"}");
                } else {
                    response.getWriter().write("{\"message\":\"Failed to add program.\"}");
                }
            } else if ("delete".equalsIgnoreCase(action)) {
                // Extract programId parameter
                int programId = json.has("programId") ? json.get("programId").getAsInt() : -1;

                if (programId <= 0) {
                    response.getWriter().write("{\"message\":\"Error: Invalid Program ID.\"}");
                    return;
                }

                System.out.println("Deleting Program ID: " + programId);

                try {
                    // Check if there are any dependent courses before deletion
                    boolean hasDependentCourses = !programService.getCoursesByProgramId(programId).isEmpty();
                    if (hasDependentCourses) {
                        response.getWriter().write("{\"message\": \"Cannot delete program. Dependent courses exist.\"}");
                        return;
                    }

                    // Delete program
                    boolean deleted = programService.deleteProgram(programId);

                    if (deleted) {
                        response.getWriter().write("{\"message\":\"Program deleted successfully.\"}");
                    } else {
                        response.getWriter().write("{\"message\": \"Failed to delete program.\"}");
                    }
                } catch (Exception e) {
                    System.err.println("Error while deleting program: " + e.getMessage());
                    response.getWriter().write("{\"message\":\"An error occurred while deleting the program: " + e.getMessage() + "\"}");
                }
            } else {
                response.getWriter().write("{\"message\":\"Invalid POST action: " + action + "\"}");
            }
        } catch (Exception e) {
            System.err.println("Error processing POST request: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().write("{\"message\":\"An error occurred: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            String action = request.getParameter("action");
            System.out.println("Received GET action: " + action);

            if ("getAll".equalsIgnoreCase(action)) {
                // Fetch all programs
                response.getWriter().write(new com.google.gson.Gson().toJson(programService.getAllPrograms()));
            } else if ("getById".equalsIgnoreCase(action)) {
                // Fetch program by ID
                int programId = Integer.parseInt(request.getParameter("programId"));
                Program program = programService.getProgramById(programId);

                if (program != null) {
                    response.getWriter().write(new com.google.gson.Gson().toJson(program));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"message\":\"Program not found\"}");
                }
            } else {
                response.getWriter().write("{\"message\":\"Invalid GET action.\"}");
            }
        } catch (Exception e) {
            System.err.println("Error processing GET request: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().write("{\"message\":\"An error occurred: " + e.getMessage() + "\"}");
        }
    }
}
