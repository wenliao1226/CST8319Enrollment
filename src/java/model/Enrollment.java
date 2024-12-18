package model;

import java.time.LocalDate;

public class Enrollment {
    private int enrollmentId;       // Enrollment_ID
    private int studentId;          // Student_ID
    private int courseId;           // Course_ID
    private LocalDate enrollmentDate; // Enrollment_Date
    private String status;
    private String courseName; 
    private int capacity;
    private String schedule;
    private Course course;
    // Status
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public Enrollment() {
        this.status = "PENDING"; // Default status
    }

    public Enrollment(int enrollmentId, int studentId, int courseId, LocalDate enrollmentDate, String status) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getters and Setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }
    

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        this.status = status;
    }
    public Course getCourse() { return course; }
    
    public void setCourse(Course course) { this.course = course; }
    
    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                ", status='" + status + '\'' +
                '}';
    }
}
