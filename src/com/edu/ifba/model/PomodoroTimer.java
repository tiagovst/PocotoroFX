package com.edu.ifba.model;

public class PomodoroTimer {
    //atributos em segundos
    private int pomodoro;
    private int shortbreak;
    private int longbreak;
    private int interval;
    
    //construtor
    public PomodoroTimer(int pomodoro, int shortbreak, int longbreak, int interval) {
        this.pomodoro = pomodoro;
        this.shortbreak = shortbreak;
        this.longbreak = longbreak;
        this.interval = interval;
    }
    
    //getters e setters

    public int getPomodoro() {
        return pomodoro;
    }

    public void setPomodoro(int pomodoro) {
        this.pomodoro = pomodoro;
    }

    public int getShortbreak() {
        return shortbreak;
    }

    public void setShortbreak(int shortbreak) {
        this.shortbreak = shortbreak;
    }

    public int getLongbreak() {
        return longbreak;
    }

    public void setLongbreak(int longbreak) {
        this.longbreak = longbreak;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
    
    
    
    
    public int getSeconds(int id) {
        switch(id){
            case 1:
                return this.getPomodoro()*60;
            
            case 2:
                return this.getShortbreak()*60;
                
            case 3:
                return this.getLongbreak()*60;
        }
        return 0;
    }
 
}