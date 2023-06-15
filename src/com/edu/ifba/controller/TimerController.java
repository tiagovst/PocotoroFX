package com.edu.ifba.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import com.edu.ifba.service.ServicePomodoroTimer;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joeziojr
 */
public class TimerController implements Initializable{
    //atributos
    @FXML
    private Button jButtonLogin;
    
    @FXML
    private Button jButtonConfig;
    
    
    @FXML
    private Button buttonStart;
    
    @FXML
    private Button buttonStop;
    
    @FXML
    private Button buttonReset;
    
    @FXML
    private Label jLabelMinutes;
    
    @FXML
    private Label jLabelSeconds;
    
    int duracaoPomodoro, duracaoSB, duracaoLB, intervalLB;
    
    private Timer timer;
    private int totalSeconds;
    private ServicePomodoroTimer service;
    
    
    //contrutor
    public TimerController() {
        
        
        this.duracaoPomodoro = 25*60;
        this.duracaoSB = 5*60;
        this.duracaoLB = 10*60;
        
        //buttonStart.setOnAction(event -> {startTimer(25);});

    }

    // Método chamado ao inicializar o controlador
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    // Método para injetar o serviço no controlador
    public void setService(ServicePomodoroTimer service) {
        this.service = service;
    }
    
    public void updateLabel(int i, String message) {
        // Verificar se o acesso ao label precisa ser feito na thread da interface gráfica
        if (!jLabelMinutes.getScene().getWindow().isShowing()) {
            return; // A janela foi fechada, interromper a atualização do label
        }

        // Atualizar o conteúdo do label na thread da interface gráfica
        if (i == 1){
            Platform.runLater(() -> jLabelMinutes.setText(message));}
        else{
            Platform.runLater(() -> {
                jLabelSeconds.setText(message);
            });
        }
    }
    
    public void onButtonStopAction() throws IOException{
        service.stopTimer();

    }
    
    
    public void onButtonStartAction() throws IOException{
        service.start();
    }
    
    
    public void onButtonResetAction() throws IOException{
        service.resetTimer();

    }
    
    

    

    
    public void onButtonLoginAction() throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/LoginView.fxml"));
        
        Stage stage = (Stage) jButtonLogin.getScene().getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();

        
    }
    
    public void onButtonConfigAction() throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/ConfigurationsView.fxml"));
        
        Stage stage = (Stage) jButtonConfig.getScene().getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();

        
    }

    public int getDuracaoPomodoro() {
        return duracaoPomodoro;
    }

    public void setDuracaoPomodoro(int duracaoPomodoro) {
        this.duracaoPomodoro = duracaoPomodoro;
    }

    public int getDuracaoSB() {
        return duracaoSB;
    }

    public void setDuracaoSB(int duracaoSB) {
        this.duracaoSB = duracaoSB;
    }

    public int getDuracaoLB() {
        return duracaoLB;
    }

    public void setDuracaoLB(int duracaoLB) {
        this.duracaoLB = duracaoLB;
    }

    public int getIntervalLB() {
        return intervalLB;
    }

    public void setIntervalLB(int intervalLB) {
        this.intervalLB = intervalLB;
    }

    
}
    

