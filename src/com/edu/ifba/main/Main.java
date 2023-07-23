
package com.edu.ifba.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.edu.ifba.controller.TimerController;

/**
 *
 * @author Gabriel
 */
public class Main extends Application {
   
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));
        
        TimerController controller = new TimerController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Pocotoro");
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