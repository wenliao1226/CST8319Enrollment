package model;

import com.google.gson.annotations.SerializedName;

public class Course {
    // Primary key field
    private int courseID;

    @SerializedName("courseName")
    private String name;

    private int programId;
    private int credits;
    private int capacity;
    private String instructor;
    private String schedule;
    private String location;
    private String description;

    // Field referenced in toString() and getters/setters but not previously declared
    private String action;

    // No-argument constructor (required if you want to use new Course() with no params)
    public Course() {
    }

    // Parameterized constructor
    public Course(int courseID, String name, int programId, int credits, int capacity,
                  String instructor, String schedule, String location, String description) {
        this.courseID = courseID;
        this.name = name;
        this.programId = programId;
        this.credits = credits;
        this.capacity = capacity;
        this.instructor = instructor;
        this.schedule = schedule;
        this.location = location;
        this.description = description;
    }

    // For compatibility: getId()/setId() map to courseID
    public int getId() {
        return courseID;
    }

    public void setId(int id) {
        this.courseID = id;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                ", programId=" + programId +
                ", credits=" + credits +
                ", capacity=" + capacity +
                ", instructor='" + instructor + '\'' +
                ", schedule='" + schedule + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
