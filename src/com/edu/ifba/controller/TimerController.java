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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author joeziojr
 */
public class TimerController implements Initializable{
    //atributos
    
    int duracaoPomodoro, duracaoSB, duracaoLB, intervalLB;
    
    private Timer timer;
    private int totalSeconds;
    
    private boolean isNewTimer = true; 
    
    @FXML
    private Button jButtonStart;
    
    @FXML
    private Button jButtonStop;
    
    @FXML
    private Button jButtonReset;
    
    @FXML
    private Label jLabelMinutes;
    
    @FXML
    private Label jLabelSeconds;
    //contrutor
    public TimerController() {
        
        
        this.duracaoPomodoro = 25;
        this.duracaoSB = 5;
        this.duracaoLB = 10;
        
        jButtonStart.setOnAction(event -> {startTimer(25);});

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    @FXML
    public void handleButtonStop() throws IOException{
        
    }
    
    @FXML
    public void handleButtonStart() throws IOException{
        startTimer(25);
    }
    
    @FXML
    public void handleButtonReset() throws IOException{
    
    }
    
    public void startTimer(int minutes) {
        totalSeconds = minutes*60;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                if (totalSeconds > 0) {
                    int minutes = totalSeconds / 60;
                    jLabelMinutes.setText(Integer.toString(minutes));
                    
                    int seconds = totalSeconds % 60;
                    jLabelSeconds.setText(Integer.toString(seconds));
                    System.out.println("Time remaining: " + minutes + " minutes " + seconds + " seconds");
                    totalSeconds--;
                } else {
                    System.out.println("Tempo Concluído");
                    stopTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000); // Schedule the task to run every 1 second
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }
    
    private void openWindow(String path) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent pane = loader.load();
    }
    
}
    

