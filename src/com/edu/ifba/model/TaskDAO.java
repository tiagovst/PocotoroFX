/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.ifba.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author bia-eduao
 */
public class TaskDAO {
    
    public static boolean insertTask(Task task){
        
        String sql = "INSERT INTO task (title, isDone, idUser) values (?, ?, ?)";

        try {
            PreparedStatement pst;
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, task.getTitle());
            pst.setString(2, task.getIsDone());
            pst.setInt(3, task.getIdUser());
            
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public static boolean deleteTask(Task task){
        
        String sql = "DROP FROM task WHERE title = ?";
        PreparedStatement pst;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, task.getTitle());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public static boolean alterStatusTask(Task task){
        
        String sql = "UPDATE task SET isDone = ? WHERE title = ?";
        PreparedStatement pst;
        
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, task.getIsDone());
            pst.setString(2, task.getTitle());
            
            pst.execute();
            pst.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        
    }
    
}
