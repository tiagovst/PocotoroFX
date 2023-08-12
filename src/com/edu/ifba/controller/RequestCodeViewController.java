/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.edu.ifba.controller;

import com.edu.ifba.model.User;
import com.edu.ifba.model.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joeziojr
 */
public class RequestCodeViewController implements Initializable {

    @FXML
    private TextField requestTxtEmail;
    @FXML
    private Button requestButtonSendCode;

    private static String emailFrom = "pocotoroconfig@gmail.com";
    private static String passwordFrom = "touzdavuoteguicp";
    private static String emailTo;
    private static String subject;
    private static String content;

    private static Properties prop;
    private static Session session;
    private static MimeMessage message;

    protected UserDAO userDAO = new UserDAO();
    protected User user = new User();

    protected int recoveryCode;
    protected String emailUser;

    public int getRecoveryCode() {
        return recoveryCode;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public Stage stage;

    @FXML
    private void handleButtonSendCode() throws IOException {
        user.setEmail(requestTxtEmail.getText());
        user = userDAO.pesquisarPorEmail(user.getEmail());

        if (user.getId() > 0) {
            sendCode();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado. Certifique-se de colocar"
                    + "o email cadastrado.", "Não cadastrado", 1);
        }
    }

    public void sendCode() throws IOException {
        emailUser = this.user.getEmail();
        this.recoveryCode = (int) (Math.random() * 1000000);
        System.out.println(recoveryCode);
        createEmail();
    }

    private void createEmail() throws IOException {
        prop = new Properties();

        emailTo = this.user.getEmail();
        subject = "Pocotoro - Código de Recuperação";
        content = "Seu código de recuperação de senha é: " + this.recoveryCode;

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.port", "587");
        prop.setProperty("mail.smtp.user", emailFrom);
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.setProperty("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(prop);

        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(subject);
            message.setText(content, "ISO-8859-1", "html");

            sendEmail();

        } catch (AddressException ex) {
            Logger.getLogger(RequestCodeViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RequestCodeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendEmail() throws IOException {
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect(emailFrom, passwordFrom);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();

            JOptionPane.showMessageDialog(null, "O Código de recuperação foi enviado para o seu email!");

            openRecovery();

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequestCodeViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RequestCodeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openRecovery() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/edu/ifba/view/PasswordRecoveryView.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
