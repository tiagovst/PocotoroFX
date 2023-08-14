package com.edu.ifba.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlaylistViewController implements Initializable {

    //Atributos
    @FXML
    Button jButtonPreviousSong;

    @FXML
    Button jButtonPlayPause;

    @FXML
    Button jButtonNextSong;
    
    @FXML
    Button jButtonCloseSong;
    
    @FXML
    Button jButtonMinimizeSong;

    @FXML
    Label jLabelSongName;
    
    private String path = "src/musics/song";
    private int actualSong = 1;
    private boolean isPlaying = false;
    private Media media = new Media(new File(path + String.valueOf(this.actualSong) + ".mp3").toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(media);

    //On Action Listeners
    public void onJButtonPreviousSongAction() {
        this.previous();
    }

    public void onJButtonNextSongAction() {
        this.next();
    }

    public void onJButtonPlayPauseAction() {
        this.playPauseSong();
    }
    
    public void onButtonCloseSongAction(){
        
    }
    
    public void onButtonMinimizeSongAction(){
    
    }
    

    //Metodos
    public void playPauseSong() {
        try {

            if (isPlaying) {
                this.isPlaying = false;
                mediaPlayer.pause();

            } else {
                this.isPlaying = true;
                mediaPlayer.play();

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void next() {
        this.mediaPlayer.stop();
        this.isPlaying = false;
        this.actualSong += 1;
        if (this.actualSong > 0 && this.actualSong < 3) {
            changeMedia(actualSong);
            playPauseSong();
        } else {
            this.actualSong = 1;
            changeMedia(actualSong);
            playPauseSong();
        }
    }

    public void previous() {
        this.mediaPlayer.stop();
        this.isPlaying = false;
        this.actualSong -= 1;
        if (this.actualSong > 0 && this.actualSong < 3) {
            changeMedia(actualSong);
            playPauseSong();
        } else {
            this.actualSong = 1;
            changeMedia(actualSong);
            playPauseSong();
        }
    }
    
    public void changeMedia(int songAtTheMoment){
        this.media = new Media(new File(path + String.valueOf(songAtTheMoment) + ".mp3").toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
        jLabelSongName.setText("song" + String.valueOf(songAtTheMoment));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jLabelSongName.setText("song" + String.valueOf(this.actualSong));
    }

}
