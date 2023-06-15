/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.ifba.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.fxml.Initializable;
//import com.edu.ifba.service.ServicePomodoroTimer;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.edu.ifba.controller.TimerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author dokidokiabr
 */
public class ConfigController {
    
    //atributos
    
    @FXML
    private Spinner<Integer> jSpinnerPomodoro;
    
    @FXML
    private Spinner<Integer> jSpinnerSB;
    
    @FXML
    private Spinner<Integer> jSpinnerLB;
    
    @FXML
    private Spinner<Integer> jSpinnerLBInterval;
    
    @FXML
    private Button jButtonUpdate;
    
    public void openMain() throws IOException{
        
        JOptionPane.showMessageDialog(null, "aaaaaaaaaaaaaaa");
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));
        
        Stage stage = (Stage) jButtonUpdate.getScene().getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
        
    }
    
    
}
