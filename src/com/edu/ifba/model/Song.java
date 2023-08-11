
package com.edu.ifba.model;

/**
 *
 * @author Gabriel
 */
public class Song {
    //Atributos
    private int id;
    private String title;
    private String filePath;
    
    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    //ToString -> Saídas
    @Override
    public String toString() {
        return "Song{" + "id=" + id + ", title=" + title + ", filePath=" + filePath + '}';
    }
    
}
