package com.edu.ifba.controller;

import com.edu.ifba.model.Task;
import com.edu.ifba.model.TaskDAO;
import com.edu.ifba.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bia-eduao
 */
public class AddTaskViewController implements Initializable{
    
    @FXML
    private TextField taskTitleTxt;
    @FXML
    private Button addTaskButton;
    @FXML
    private Button cancelTaskButton;
    
    LoginViewController lvc = new LoginViewController();

    /**
     * Initializes the controller class.
     */
    
    public void onAddTaskButtonAction() throws IOException{
        addTask(taskTitleTxt.getText());
        
    }
    
    public void onCancelTaskButton() throws IOException{
        cancel();
    }
    
    private void addTask(String txtTitle) throws IOException {
        User user = lvc.usuarioLogado;
        Task task = new Task();
        
        task.setIdUser(lvc.usuarioLogado.getId());
        task.setTitle(txtTitle);
        task.setIsDone("Não");
        
        user.getTasklist().add(task);
        
        if(TaskDAO.insertTask(task)){
                Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));

                Stage stage = (Stage) addTaskButton.getScene().getWindow();

                Scene newScene = new Scene(root);
                stage.setScene(newScene);
                stage.show();
                JOptionPane.showMessageDialog(null, "User cadastrado com sucesso");
        }else if(!TaskDAO.insertTask(task)){
            JOptionPane.showMessageDialog(null, "Erro ao adicionar task.");
        }
        
        JOptionPane.showMessageDialog(null, "Task adicionada!");
        
    }
    
    private void cancel() throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));
        Stage stage = (Stage) cancelTaskButton.getScene().getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
