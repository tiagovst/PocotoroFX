/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.ifba.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author bia-eduao
 */
public class PasswordRecoveryViewController {
    
    @FXML
    private TextField recoveryTxtCode;
    @FXML
    private TextField recoveryTxtNewPassword;
    @FXML
    private TextField recoveryTxtConfirmPassword;
    @FXML
    private Button recoveryButtonResetPassword;
    
    RequestCodeViewController request;
    private int recoveryCode = request.getRecoveryCode();
    
    @FXML
    public void handleButtonResetPassword(){
        if(Integer.parseInt(recoveryTxtCode.getText())==recoveryCode){
            if(recoveryTxtNewPassword.getText().equals(recoveryTxtConfirmPassword.getText())){
                this.request.user.setPassword(recoveryTxtNewPassword.getText());
                this.request.userDAO.alterarSenha(this.request.user);
                
                JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Senhas não coincidem.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Código de Recuperação incorreto.");
        }
    }
}
