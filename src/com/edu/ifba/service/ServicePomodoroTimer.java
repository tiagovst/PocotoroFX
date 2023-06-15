package com.edu.ifba.service;

import com.edu.ifba.model.PomodoroTimer;
import com.edu.ifba.controller.TimerController;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author gabriel
 */
public class ServicePomodoroTimer extends TimerController{
    TimerController controller;
    String txtFinished;
    private Timer timer;
    private int totalSeconds, actualSeconds;
    private int idState = 1;
    private boolean isNewTimer;
    public PomodoroTimer value = PomodoroTimer(25, 5, 10, 4);
    private int intervalLB;

    public ServicePomodoroTimer(TimerController controller) {
        this.intervalLB = value.getInterval();
        this.controller = controller;
    }
    
    //métodos
    public void start() {
        if (isNewTimer){
            resetTimer();
        }else{
            startTimer(this.actualSeconds);
        }
    }
    
    public void update(){
    }
    
    
    public void startTimer(int seconds) {
        totalSeconds = seconds;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                if (totalSeconds > 0) {
                    actualSeconds = totalSeconds;
                    int minutes = totalSeconds / 60;
                    controller.updateLabel(1, Integer.toString(minutes));
                    int seconds = totalSeconds % 60;
                    controller.updateLabel(2, Integer.toString(seconds));
                    System.out.println("Time remaining: " + minutes + " minutes " + seconds + " seconds");
                    totalSeconds--;
                } else {
                    
                    System.out.println(txtFinished);
                    switch (idState) {
                        case 1:
                            if(intervalLB == 0){
                                setIdState(3);
                            }
                            else{
                                setIdState(2);
                            }
                            intervalLB -= 1;
                            break;
                        case 2:
                            setIdState(1);
                            break;
                        case 3:
                            setIdState(1);
                            break;
                        default:
                            break;
                            
                    }
                    stopTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000); // Schedule the task to run every 1 second
    }
    
    public void stopTimer() {
        if (timer != null) {
            this.isNewTimer = false;
            
            timer.cancel();
            Toolkit.getDefaultToolkit().beep();
            timer.purge();
        }
    }
    
    public void resetTimer(){
        this.totalSeconds = value.getSeconds(idState);
        startTimer(this.totalSeconds);
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    private PomodoroTimer PomodoroTimer(int i, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    

}
