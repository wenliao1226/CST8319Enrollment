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

    // 添加新课程
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    // 根据课程 ID 获取课程信息
    public Course getCourseById(int courseId) {
        return courseDAO.getCourseById(courseId);
    }
    
    //get course information according by key words
    public List<Course> searchCourses(String courseName, String courseID, String program) {
    return courseDAO.searchCourses(courseName, courseID, program);
}


    // 获取所有课程
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    // 删除课程
    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }
}
