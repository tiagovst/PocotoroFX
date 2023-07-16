package com.edu.ifba.controller;

import com.edu.ifba.model.PomodoroTimer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
//import com.edu.ifba.service.ServicePomodoroTimer;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
//import javafx.event.ActionEvent;
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
    
    int intervalLB;
        
    ArrayList<String> listMinSec = new ArrayList<>();
    
    String txtFinished;
    
    private Timer timer;
    
    private int totalSeconds, actualSeconds;
    private int idState = 1;
    
    private boolean isNewTimer;
    private boolean isTimerRunning = false;
    
    
    public PomodoroTimer value;
    
    public String minutesStr, secondsStr;
        
    //construtor
    public TimerController() {
        System.out.println(this.getValue());
        if(value == null){
            this.value = new PomodoroTimer(25, 5, 10, 4);
        }
        
        this.intervalLB = value.getInterval();
        listMinSec.add(Integer.toString(value.getPomodoro()));
        listMinSec.add("00");
 
        
        this.actualSeconds = value.getSeconds(idState);        
    }
    
    public TimerController(PomodoroTimer timerPomodoro) {
        System.out.println(this.getValue());
        value = timerPomodoro;
        
        this.intervalLB = value.getInterval();
        listMinSec.add(Integer.toString(value.getPomodoro()));
        listMinSec.add("00");
 
        
        this.actualSeconds = value.getSeconds(idState);        
    }
    


    // Métodos para os botões
    
    public void onButtonStopAction() throws IOException{
        this.stopTimer();
    }
    
    
    public void onButtonStartAction() throws IOException{
        this.start();
    }
    
    
    public void onButtonResetAction() throws IOException{
        this.resetTimer();

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
    
    //Função para atualizar as labels
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


            //Métodos do timer
    //Função para definir se o tempo está no meio ou é um tempo novo
    public void start() {
        if(isTimerRunning == false){
            isTimerRunning = true;
            if (isNewTimer){
                this.stopTimer();
                this.resetTimer();
            }else{
                startTimer(this.actualSeconds);
            }
        }
    }
    
    //Função para iniciar o timer
    public void startTimer(int seconds) {
        totalSeconds = seconds;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (totalSeconds > 0) {
                    actualSeconds = totalSeconds;
                    //separando minutos e segundos
                    int minutes = totalSeconds / 60;
                    int seconds = totalSeconds % 60;
                    
                    //colocando eles no interável e na tela
                    minutesStr = String.format("%02d", minutes);
                    listMinSec.set(0, minutesStr);
                    updateLabel(1, listMinSec.get(0));
                    
                    secondsStr = String.format("%02d", seconds);
                    listMinSec.set(1, secondsStr);
                    updateLabel(2, listMinSec.get(1));

                    System.out.println("Time remaining: " + minutes + " minutes " + seconds + " seconds");
                    totalSeconds--;
                    //return getList();
                } else {
                    
                    System.out.println(txtFinished);
                    switch (idState) {
                        case 1 -> {
                            if(intervalLB == 0){
                                setIdState(3);
                            }
                            else{
                                setIdState(2);
                            }
                            intervalLB -= 1;
                        }
                        case 2 -> setIdState(1);
                        case 3 -> setIdState(1);
                        default -> {
                        }
                            
                    }
                    stopTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000); // Schedule the task to run every 1 second
    }
    
    
    //Função para parar o timer
    public void stopTimer() {
        if (timer != null) {
            this.isNewTimer = false;
            
            timer.cancel();
            Toolkit.getDefaultToolkit().beep();
            timer.purge();
        }
        
        isTimerRunning = false;
        actualSeconds = value.getSeconds(idState);
    }
    
    //Função para resetar o timer
    public void resetTimer(){
        stopTimer();
        setIdState(1);
        this.totalSeconds = value.getSeconds(idState);
        start();
    }

    //getters e setters
    public void setIdState(int idState) {
        this.idState = idState;
    }

    public ArrayList<String> getList() {
        return listMinSec;
    }

    public PomodoroTimer getValue() {
        return value;
    }
    
    public void setValue(PomodoroTimer value) {
        this.value = value;
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Bleh
    }

    
}
    

