/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.edu.ifba.service;
//
//import static com.edu.ifba.model.PasswordEncryptor.encryptPassword;
//import com.edu.ifba.model.User;
//import com.edu.ifba.model.UserDAO;
//import javafx.fxml.FXML;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author joeziojr
// */
//public class RegisterService {
// 
//    //Importarei algo para pegar os dados da tela de cadastro
//    //private final RegisterView registerView;
//    private final UserDAO userDAO;
//    
//    
//    @FXML
//    private TextField registerTxtName;
//    
//    @FXML
//    private TextField registerTxtEmail;
//    
//    @FXML
//    private PasswordField registerPasswordTxt;
//    
//    @FXML
//    private PasswordField registerPasswordTxtConfirm;
//    
//    
//
//    public RegisterService(/*RegisterView register*/) {
//        //this.registerView = register;
//        this.userDAO = new UserDAO();
//        
//    }
//    
//    
//    //Método de cadastro
//    public void cadastrar(String txtEmail, String txtName, String txtPassword){
//        
//        if (validarCampos(registerTxtEmail.getText(), registerTxtName.getText(), registerPasswordTxt.getText(), registerPasswordTxtConfirm.getText())){
//            User user = new User();
//            user.setEmail(txtEmail);
//            user.setName(txtName);
//            user.setPassword(encryptPassword(txtPassword));
//            
//            userDAO.insert(user);
//            JOptionPane.showMessageDialog(null, "User cadastrado com sucesso");
//            
//        }
//        
//        
//    }
//    
//    
//    /*Método que confere se os dados foram inseridos e se as senhas batem
//        Ele ainda não verifica a existência do usuário no banco*/
//    
//    public boolean validarCampos(String txtEmail, String txtName, String txtPassword, String txtPasswordConfirm){
//        
//
//        boolean isIt = false;
//        
//        
//        if ((txtEmail.equals("") == false) &&
//            (txtName.equals("") == false) &&
//            (txtPassword.equals("") == false)){
//            
//            if(txtPassword.equals(txtPasswordConfirm)){
//                isIt = true;
//            }else if(txtPassword.equals(txtPasswordConfirm) == false){
//                JOptionPane.showMessageDialog(null, "Senhas não coincidem");
//                isIt = false;
//            }
//            
//        }
//        else{
//            JOptionPane.showMessageDialog(null, "Há campos vazios");
//            isIt = false;
//        }
//        return isIt;
//    }
//    
//}
