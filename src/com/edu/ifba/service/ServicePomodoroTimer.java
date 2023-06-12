package com.edu.ifba.service;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import model.PomodoroTimer;

/**
 *
 * @author gabriel
 */
public class ServicePomodoroTimer {
    //atributos
    private MainView mainView;
    private Timer timer;
    private int secondsPomodoro;
    private int secondsSB;
    private int secondsLB;
    private int secondsACTUAL;
    private int intervalLB;
    private int idState;
    private String txtFinished;
    private int cont;
    private boolean autoStart;
    
    private PomodoroTimer pomodoro;
    private PomodoroTimer shortBreak;
    private PomodoroTimer longBreak;
    
    
    
    //construtor
    public ServicePomodoroTimer() {
        this.idState = 1;
        this.autoStart = true;
        
        this.intervalLB = 4;
        
        this.secondsPomodoro = pomodoro.getSeconds();
        this.secondsSB = shortBreak.getSeconds();
        this.secondsLB = longBreak.getSeconds();
    }
    
    public ServicePomodoroTimer(MainView mainView, int duracaoPomodoro, int duracaoSB, int duracaoLB) {
        this.idState = 1;
        this.autoStart = true;
        
        this.mainView = mainView;
        this.pomodoro = new PomodoroTimer(duracaoPomodoro, 1);
        this.shortBreak = new PomodoroTimer(duracaoSB, 2);
        this.longBreak = new PomodoroTimer(duracaoLB, 2);
        
        this.intervalLB = 4;
        
        this.secondsPomodoro = pomodoro.getSeconds();
        this.secondsSB = shortBreak.getSeconds();
        this.secondsLB = longBreak.getSeconds();
    }
    
    //métodos
    public void start(PomodoroTimer value) {
        this.cont = 1;
        
        if(value.getIdPomodoro() == 1){
                this.txtFinished = "Pomodoro concluído!";}
        else if(value.getIdPomodoro() == 1){
                this.txtFinished = "Descanso finalizado!";
                System.out.println("2"+value.getIdPomodoro());}
        else if(value.getIdPomodoro() == 1){
                this.txtFinished = "Descanso finalizado!";
                System.out.println("3"+value.getIdPomodoro());
        }
        System.out.println(this.txtFinished);
        
        this.secondsACTUAL = value.getSeconds();
        playTimer();
        
       
    }
    
    public void start() {
  
        this.secondsACTUAL = this.pomodoro.getSeconds();
        idState = this.pomodoro.getIdPomodoro();
                System.out.println("awn"+idState);
        playTimer();
        this.cont = 1;
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            System.out.println("Temporizador parado.");
            Toolkit.getDefaultToolkit().beep();
            
        }
    }
    
    public void update(int pomodoro, int SB, int LB){
       stop();
       this.pomodoro.setDuration(pomodoro);
       this.longBreak.setDuration(LB);
       this.shortBreak.setDuration(SB);
       start(this.pomodoro);
    }
    
    public void defineState(){
        switch (this.idState) {
            case 1:
                this.start(this.pomodoro);
                break;
            case 2:
                this.start(this.shortBreak);
                break;
            case 3:
                this.start(this.longBreak);
                break;
            default:
                break;
        }
    }
    ;
    public void playTimer(){
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (secondsACTUAL <= 0) {
                    timer.cancel();
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println(txtFinished);
                    switch (idState) {
                        case 1:
                            if(intervalLB == 0){
                                idState = 3;
                            }
                            else{
                                idState = 2;
                            }
                            intervalLB -= 1;
                            break;
                        case 2:
                            idState = 1;
                            break;
                        case 3:
                            idState = 1;
                            break;
                        default:
                            break;
                            
                    }
                    System.out.println("nyahw"+idState);
                    
                } else {
                    int minutes = secondsACTUAL / 60;
                    int remainingSeconds = secondsACTUAL % 60;
                    if (secondsACTUAL > 0) { 
                        //tarefa a ser realizada
                        System.out.printf("Tempo restante: %d minutos %d segundos%n", minutes, remainingSeconds);
                        mainView.getLblMinutes().setText(String.format("%02d", minutes));
                        mainView.getLblSeconds().setText(String.format("%02d", remainingSeconds));
                    }
                    secondsACTUAL--;
                }
            }
        }, 0, 1000);
    }

    public int getCont() {
        return cont;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }
    
    
    

}
