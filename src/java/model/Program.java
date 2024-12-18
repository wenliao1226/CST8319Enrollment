package model;

public class Program {
    private int programId; // Primary key
    private String name; // Name of the program

    // Default constructor
    public Program() {
    }

    // Parameterized constructor
    public Program(int programId, String name) {
        this.programId = programId;
        this.name = name;
    }

    // Getters and Setters
    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Program{" +
                "programId=" + programId +
                ", name='" + name + '\'' +
                '}';
    }
}
