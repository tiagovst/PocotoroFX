package com.edu.ifba.controller;

import com.edu.ifba.model.User;
import com.edu.ifba.model.UserDAO;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author joeziojr
 */
public class LoginViewController {

    @FXML
    private TextField loginEmailTxt;

    @FXML
    private Button loginEnterButton;

    @FXML
    private Button loginForgotButton;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Button loginRegisterButton;

    private UserDAO userDao = new UserDAO();
    protected User usuarioLogado;

    //Método que  verifica a existência de uma conta, verificando o email
    public boolean fazerLogin() throws IOException {
        if (validarCampos()) {
            User us = userDao.pesquisarUser(loginEmailTxt.getText(),
                    loginEmailTxt.getText(), loginPasswordField.getText());
            if (us.getId() > 0) {
                openMain();
                usuarioLogado = us;
                return true;
            }
            if (us.getId() == 0) {
                JOptionPane.showMessageDialog(null, "User não cadastrado");
                return false;
            }
        }
        return false;
    }

    public boolean validarCampos() {
        if (loginEmailTxt.getText().equals("") || (loginPasswordField.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Há campos vazios");
            return false;
            //Implementar lógica aqui
        } else {
            return true;
        }
    }

    public void openRegister(ActionEvent a) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/RegisterView.fxml"));

        Stage stage = (Stage) loginRegisterButton.getScene().getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();

    }

    public void openMain() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/edu/ifba/view/MainView.fxml"));

        TimerController controller = new TimerController();

        loader.setController(controller);
        Parent root = loader.load();

        Stage stage = (Stage) loginRegisterButton.getScene().getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();

    }

    public void openRequestCodeView() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/RequestCodeView.fxml"));

        Stage stage = (Stage) loginRegisterButton.getScene().getWindow();

        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();

    }

}
