package test;

import model.Course;
import model.Department;
import service.CourseService;
import service.DepartmentService;

public class CourseServiceTest {
    public static void main(String[] args) {
        // Create service instances
        CourseService courseService = new CourseService();
        DepartmentService departmentService = new DepartmentService();

        // Ensure Department exists before adding a Course
        Department department = new Department(1, "Computer Science", "CS Program");
        boolean departmentResult = departmentService.addDepartment(department);
        System.out.println("Add department result: " + (departmentResult ? "Success" : "Failed"));

        // Test adding a course
        Course newCourse = new Course(0, "Math 101", 1, 3, 30, "Dr. Smith", "MWF 10-11", "Room 101", "Basic math course");
        boolean addCourseResult = courseService.addCourse(newCourse);
        System.out.println("Add course result: " + (addCourseResult ? "Success" : "Failed"));

        // Test fetching all courses
        System.out.println("All courses:");
        courseService.getAllCourses().forEach(System.out::println);

        // Test fetching a course by ID
        Course courseById = courseService.getCourseById(1); // Replace with an existing Course_ID
        System.out.println("Course by ID: " + courseById);

        // Test deleting a course
        boolean deleteCourseResult = courseService.deleteCourse(1); // Replace with an existing Course_ID
        System.out.println("Delete course result: " + (deleteCourseResult ? "Success" : "Failed"));
    }
}
