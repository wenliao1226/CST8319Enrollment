package test;

import model.Course;
import service.CourseService;

import java.util.List;

public class CourseServiceTest {

    public static void main(String[] args) {
        CourseServiceTest tester = new CourseServiceTest();
        tester.testAddCourse();
        tester.testGetCourseById();
        tester.testGetAllCourses();
       /* tester.testDeleteCourse()*/;
    }

    public void testAddCourse() {
        CourseService courseService = new CourseService();
        Course course = new Course(0, "Service Test Course", 1, 4, 30, "Prof. Service", "TTH 2-3", "Room 102", "Testing service layer");

        int courseId = courseService.addCourse(course);
        if (courseId > 0) {
            System.out.println("Test Add Course: PASSED (Generated Course ID: " + courseId + ")");
        } else {
            System.out.println("Test Add Course: FAILED");
        }
    }

    public void testGetCourseById() {
        CourseService courseService = new CourseService();
        int testCourseId = 3; // Ensure this matches an existing ID in the correct database

        Course course = courseService.getCourseById(testCourseId);

        if (course != null) {
            System.out.println("Test Get Course By ID: PASSED");
            System.out.println("Retrieved Course: " + course);
        } else {
            System.out.println("Test Get Course By ID: FAILED");
        }
    }


    public void testGetAllCourses() {
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getAllCourses();

        if (courses != null && !courses.isEmpty()) {
            System.out.println("Test Get All Courses: PASSED");
            System.out.println("Number of Courses: " + courses.size());
        } else {
            System.out.println("Test Get All Courses: FAILED");
        }
    }

  /*  public void testDeleteCourse() {
        CourseService courseService = new CourseService();
        Course course = new Course(0, "Service Test Delete", 1, 3, 20, "Prof. Delete", "MWF 12-1", "Room 104", "Testing deletion in service");
        int courseId = courseService.addCourse(course);

        if (courseId > 0) {
            boolean result = courseService.deleteCourse(courseId);
            System.out.println("Test Delete Course: " + (result ? "PASSED" : "FAILED"));

            Course deletedCourse = courseService.getCourseById(courseId);
            if (deletedCourse == null) {
                System.out.println("Verification: Course successfully deleted.");
            } else {
                System.out.println("Verification: Course still exists.");
            }
        } else {
            System.out.println("Test Delete Course: FAILED (Course not added)");
        }
    }*/
}
