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
import java.io.IOException;
import javafx.scene.control.Button;
import com.edu.ifba.model.PomodoroTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author dokidokiabr
 */
public class ConfigController implements Initializable{
    
    //atributos
    PomodoroTimer pomodoroTimer;
    TimerController timer;
    TimerController timerNewController;
    
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
    
    private Parent root;
    
    public void onButtonUpdateAction() throws IOException {
        openMain(jSpinnerPomodoro.getValueFactory().getValue(), jSpinnerSB.getValueFactory().getValue(), jSpinnerLB.getValueFactory().getValue(), jSpinnerLBInterval.getValueFactory().getValue());
    }
    
    public void openMain(int pomodoro, int sb, int lb, int interval) throws IOException{
        pomodoroTimer = new PomodoroTimer(pomodoro, sb, lb, interval);
        timerNewController = new TimerController(pomodoroTimer);
        //Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));	
        //root = loader.load();
        
        //controller.setValue(pomodoroTimer);
        
        loader.setController(timerNewController);
        root = loader.load();
        
        TimerController controller = loader.getController();


        
        System.out.println("aqui viado");
        System.out.println(controller.getValue().getPomodoro());
        System.out.println(timerNewController.getValue().getPomodoro());
        System.out.println("-----\n");
        
        Stage stage = (Stage) jButtonUpdate.getScene().getWindow();

        Scene newScene = new Scene(root);

        stage.setScene(newScene);
        
        stage.show();
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.timer = new TimerController();
        
        SpinnerValueFactory factoryPomodoro = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 25);
        factoryPomodoro.setValue(timer.getValue().getPomodoro());
        jSpinnerPomodoro.setValueFactory(factoryPomodoro);
        
        SpinnerValueFactory factorySB = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 5);
        factorySB.setValue(timer.getValue().getShortbreak());
        jSpinnerSB.setValueFactory(factorySB);
        
        SpinnerValueFactory factoryLB = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 10);
        factoryLB.setValue(timer.getValue().getLongbreak());
        jSpinnerLB.setValueFactory(factoryLB);
        
        SpinnerValueFactory factoryInterval = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, 4);
        factoryInterval.setValue(timer.getValue().getInterval());
        jSpinnerLBInterval.setValueFactory(factoryInterval);
        
        
        
    }
    
    
}

