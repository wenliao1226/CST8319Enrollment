/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CourseDAO;
import model.Course;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO = new CourseDAO();

   
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }
    
    public boolean isDuplicateCourse(int courseId, String courseName) {
    return courseDAO.isDuplicateCourse(courseId, courseName);
}


  
    public Course getCourseById(int courseId) {
        return courseDAO.getCourseById(courseId);
    }
    
  
    public List<Course> searchCourses(String courseName, String courseID, String program) {
    return courseDAO.searchCourses(courseName, courseID, program);
}


   
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

   
    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }
}
