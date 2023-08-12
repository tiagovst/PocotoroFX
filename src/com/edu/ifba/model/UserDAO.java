/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.ifba.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static com.edu.ifba.model.PasswordEncryptor.encryptPassword;

/**
 *
 * @author joeziojr
 */
public class UserDAO {

    public static boolean insert(User user) {

        String sql = "INSERT INTO user (name, email, password) values (?, ?, ?)";

        try {
            PreparedStatement pst;
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public User pesquisarUser(String email, String name, String password) {
        String sql = "SELECT * FROM user WHERE email = ? OR name = ? AND password = ?";
        User user = new User();

        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);

            pst.setString(1, email);
            pst.setString(2, name);
            pst.setString(3, encryptPassword(password));
            rs = pst.executeQuery();

            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca!", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }

        return user;
    }

    ///Função que verifica e existência de um email no banco
    public User pesquisarPorEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

        User user = new User();

        PreparedStatement pst;
        ResultSet rs;

        try {

            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            while (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("id"));
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return user;
    }

    //Função que verifica e existência de um nome no banco
    public User pesquisarPorNome(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";

        User user = new User();

        PreparedStatement pst;
        ResultSet rs;

        try {

            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, name);
            rs = pst.executeQuery();

            while (rs.next()) {
                user.setName(rs.getString("name"));
                user.setId(rs.getInt("id"));
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return user;
    }

    public boolean alterarSenha(User user) {

        String sql = "UPDATE user SET password = ? WHERE email = ?";

        PreparedStatement pst;
        try {
            pst = Conexao.getConexao().prepareStatement(sql);
            pst.setString(1, user.getPassword());
            pst.setString(2, user.getEmail());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }

}
