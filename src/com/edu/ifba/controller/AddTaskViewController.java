/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.edu.ifba.controller;

import com.edu.ifba.model.Task;
import com.edu.ifba.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bia-eduao
 */
public class AddTaskViewController implements Initializable{
    
    @FXML
    private TextField taskTitleTxt;
    @FXML
    private Button addTaskButton;
    
    LoginViewController lvc = new LoginViewController();

    /**
     * Initializes the controller class.
     */
    
    public void onAddTaskButtonAction(){
        addTask(taskTitleTxt.getText());
        
    }
    
    private void addTask(String txtTitle) {
        User user = lvc.usuarioLogado;
        Task task = new Task();
        
        task.setTitle(txtTitle);
        task.setIsDone("Não");
        
        user.getTasklist().add(task);
        JOptionPane.showMessageDialog(null, "Task adicionada!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
