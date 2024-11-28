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
    
    // 根据课程名字获取课程
    public List<Course> getCoursesByName(String courseName) {
        return courseDAO.getCoursesByName(courseName);
    }

    // 根据 program 获取课程
    public List<Course> getCoursesByProgram(String program) {
        return courseDAO.getCoursesByProgram(program);
    }
    
        // 添加 searchCourses 方法，根据多个条件搜索课程
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
