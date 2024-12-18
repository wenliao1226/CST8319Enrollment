package service;

import dao.CourseDAO;
import model.Course;

import java.util.List;

public class CourseService {
    private final CourseDAO courseDAO = new CourseDAO();

    // Add a new course
    public int addCourse(Course course) {
        if (course.getProgramId() <= 0) {
            System.out.println("Invalid Program ID");
            return -1; // Indicate failure
        }
        if (course.getCredits() <= 0) {
            System.out.println("Invalid credits");
            return -1;
        }
        if (course.getCapacity() <= 0) {
            System.out.println("Invalid capacity");
            return -1;
        }

        // Delegate to DAO and return the generated course ID
        return courseDAO.addCourse(course);
    }

    // Get a course by its ID
    public Course getCourseById(int courseId) {
        if (courseId <= 0) {
            System.out.println("Invalid Course ID");
            return null;
        }
        return courseDAO.getCourseById(courseId);
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

 // Search for courses
    public List<Course> searchCourses(String courseName, String courseId, String programId) {
        return courseDAO.searchCourses(
                courseName != null && !courseName.trim().isEmpty() ? courseName : null,
                courseId != null && !courseId.trim().isEmpty() ? courseId : null,
                programId != null && !programId.trim().isEmpty() ? programId : null
        );
    }

    // Delete a course by ID
    public boolean deleteCourse(int courseId) {
        if (courseId <= 0) {
            System.out.println("Invalid Course ID");
            return false;
        }
        return courseDAO.deleteCourse(courseId);
    }

    public boolean updateCourse(Course course) {
        if (course.getCourseID() <= 0) {
            System.out.println("Invalid Course ID for update");
            return false;
        }
        if (course.getCapacity() <= 0 || course.getCredits() <= 0 || course.getProgramId() <= 0) {
            System.out.println("Invalid attributes for update");
            return false;
        }
        // Call DAO
        return courseDAO.updateCourse(course);
    }
    
    public boolean enrollStudentInCourse(int studentID, int courseID) {
        return courseDAO.enrollStudent(studentID, courseID);
    }

    public boolean dropStudentFromCourse(int studentID, int courseID) {
        return courseDAO.dropStudent(studentID, courseID);
    }
}
                		
                		
