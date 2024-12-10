/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ProgramDAO;
import model.Program;

/**
 *
 * @author wenli
 */


import java.util.List;

public class ProgramService {
    private ProgramDAO programDAO = new ProgramDAO();

    public List<Program> getAllPrograms() {
        return programDAO.getAllPrograms(); // 从DAO层获取数据
    }
}
