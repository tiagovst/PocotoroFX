package com.edu.ifba.controller;

import java.io.File;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Gabriel
 */
public class PlaylistViewController {

    //Atributos
    @FXML
    Button jButtonPreviousSong;

    @FXML
    Button jButtonPlayPause;

    @FXML
    Button jButtonNextSong;

    @FXML
    Label jLabelSongName;

    private String path = "src/musics/song";
    private int actualSong = 1;
    private boolean isPlaying = false;
    private Media media = new Media(new File(path + String.valueOf(this.actualSong) + ".mp3").toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(media);

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

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
        this.setMedia(new Media(new File(path + String.valueOf(this.actualSong) + ".mp3").toURI().toString()));
        this.setMedia(media);
        jLabelSongName.setText("song" + String.valueOf(this.actualSong));
        playPauseSong();

    }

    public void previous() {
        this.mediaPlayer.stop();
        this.isPlaying = false;
        this.actualSong -= 1;
        this.setMedia(new Media(new File(path + String.valueOf(this.actualSong) + ".mp3").toURI().toString()));
        this.setMedia(media);
        jLabelSongName.setText("song" + String.valueOf(this.actualSong));
        playPauseSong();
    }

}
