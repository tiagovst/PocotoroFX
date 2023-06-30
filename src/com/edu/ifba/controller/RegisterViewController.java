/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.edu.ifba.controller;

import static com.edu.ifba.model.PasswordEncryptor.encryptPassword;
import com.edu.ifba.model.User;
import com.edu.ifba.model.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joeziojr
 */
public class RegisterViewController{

    @FXML
    private TextField registerTxtEmail;
    @FXML
    private PasswordField registerPasswordTxt;
    @FXML
    private Button registerButton;
    @FXML
    private TextField registerTxtName;
    @FXML
    private PasswordField registerPasswordTxtConfirm;
    
    private UserDAO user = new UserDAO(); 

    /**
     * Initializes the controller class.
     */ 
    
    
     //Método de cadastro
    
    public void onRegisterButtonAction() throws IOException{
        cadastrar(registerTxtEmail.getText(), registerTxtName.getText(), registerPasswordTxt.getText());
    }
    
    public void cadastrar(String txtEmail, String txtName, String txtPassword) throws IOException{
        if (validarCampos(registerTxtEmail.getText(), registerTxtName.getText(), registerPasswordTxt.getText(), registerPasswordTxtConfirm.getText())){
            User user = new User();
            user.setEmail(txtEmail);
            user.setName(txtName);
            user.setPassword(encryptPassword(txtPassword));
            
            if(UserDAO.insert(user)){

                Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/LoginView.fxml"));

                Stage stage = (Stage) registerButton.getScene().getWindow();

                Scene newScene = new Scene(root);
                stage.setScene(newScene);
                stage.show();
                JOptionPane.showMessageDialog(null, "User cadastrado com sucesso");
                
            }else if (!UserDAO.insert(user)){
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
            }
        }
        
        
    }
    
    
    /*Método que confere se os dados foram inseridos e se as senhas batem
        Ele também verifica a existência do usuário no banco*/
    
    public boolean validarCampos(String txtEmail, String txtName, String txtPassword, String txtPasswordConfirm){
        

        boolean isIt = false;
        
        User us = user.pesquisarUser(txtEmail, txtName);           
        
        if(us.getId() > 0){
           JOptionPane.showMessageDialog(null, "Nome de usuário, ou email já cadastrado");
        }else if (us.getId() == 0){
            if ((txtEmail.equals("") == false) &&
                (txtName.equals("") == false) &&
                (txtPassword.equals("") == false)){

                if(txtPassword.equals(txtPasswordConfirm)){
                    isIt = true;
                }else if(txtPassword.equals(txtPasswordConfirm) == false){
                    JOptionPane.showMessageDialog(null, "Senhas não coincidem");
                    isIt = false;
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Há campos vazios");
                isIt = false;
            }
            
        }
        return isIt;
    }

   
}
    
