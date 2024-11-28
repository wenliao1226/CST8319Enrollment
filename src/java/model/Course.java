package model;

public class Course {
    private int courseId;          // Course_ID
    private String courseName;     // Course_Name
    private int departmentId;      // Department_ID
    private int credits;           // Credits
    private int capacity;          // Capacity
    private String instructor;     // Instructor
    private String schedule;       // Schedule
    private String location;       // Location
    private String description;    // Description
    private String program;        // Program

    public Course() {}

    public Course(int courseId, String courseName, int departmentId, int credits, int capacity, 
                  String instructor, String schedule, String location, String description, String program) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.departmentId = departmentId;
        this.credits = credits;
        this.capacity = capacity;
        this.instructor = instructor;
        this.schedule = schedule;
        this.location = location;
        this.description = description;
        this.program = program;
    }

    public Course(int aInt, String string, int aInt0, int aInt1, String string0, String string1, String string2, String string3, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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