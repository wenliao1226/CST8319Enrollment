/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Course {
    private int courseId;          // Course_ID
    private String courseName;     // Course_Name
    private int programId;         // programId;
    private int credits;           // Credits
    private int capacity;          // Capacity
    private String instructor;     // Instructor
    private String schedule;       // Schedule
    private String location;       // Location
    private String description;    // Description
    private String program; 

    public Course() {}
    
    public Course(int courseId, String courseName, int programId, int capacity, String instructor, String schedule, String location, String description) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.programId = programId;
    this.capacity = capacity;
    this.instructor = instructor;
    this.schedule = schedule;
    this.location = location;
    this.description = description;
}


public Course(int courseId, String courseName, int programId, int credits, int capacity, String instructor, String schedule, String location, String description, String program) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.programId = programId;
    this.credits = credits;
    this.capacity = capacity;
    this.instructor = instructor; // 确保 instructor 是 String
    this.schedule = schedule;
    this.location = location;
    this.description = description;
    this.program = program;
}



    public Course(int courseId, String courseName, int programId, int credits, int capacity, String instructor, String schedule, String location, String description) {
        this(courseId, courseName, programId, credits, capacity, instructor, schedule, location, description, null);
    }

public Course(int courseId, String courseName, int programId, int capacity, String instructor, String schedule, String location, String description, String program) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.programId = programId;
    this.capacity = capacity;
    this.instructor = instructor;
    this.schedule = schedule;
    this.location = location;
    this.description = description;
    this.program = program; 
}




    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", program='" + program + '\'' +
                '}';
    }
}
