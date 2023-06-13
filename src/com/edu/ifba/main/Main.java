
package com.edu.ifba.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));
        
        //Font.loadFont(getClass().getResourceAsStream("/com/edu/ifba/view/fonts/Lato-Regular.ttf"), 20);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pocotoro");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
