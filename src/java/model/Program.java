/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wenli
 */
public class Program {
    private int programId;          // 对应Program_ID
    private String programName;     // 对应Program_Name

    public Program(int programId, String programName) {
        this.programId = programId;
        this.programName = programName;
    }

    public int getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }
}
