package com.edu.ifba.model;

public class PomodoroTimer {
    //atributos
    private int minutes;
    private final int idPomodoro;
    
    //construtor
    public PomodoroTimer(int duration, int idPomodoro) {
        this.minutes = duration;
        this.idPomodoro = idPomodoro;
    }
    
    //getters e setters
    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getIdPomodoro() {
        return idPomodoro;
    }
    
 
}