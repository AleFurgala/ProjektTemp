package com.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class AdminAccounts {

    private Connection connection;


    public AdminAccounts(Connection connection) {
        this.connection = connection;
    }


    private Long id;

    private String password;
    private String login;

    private String username;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPasswordBasedLogin(String login) throws SQLException {
        String query = "SELECT haslo FROM konta_administratorow WHERE login =  '" + login + "'";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println();
            while (rs.next())
               return  rs.getString(1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "0";
    }
    public String getNameBasedLogin(String login) throws SQLException {
        String query = "SELECT nazwa_uzytkownika FROM konta_administratorow WHERE login =  '" + login + "'";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println();
            while (rs.next())
                return  rs.getString(1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "0";
    }

    public void addAdminAccount(String login, String password, String userName) throws SQLException{

        try {
            Statement stmt = connection.createStatement();

            String query = "INSERT INTO konta_administratorow(login, haslo, nazwa_uzytkownika) VALUES('" + login + "' , '" + dataEncryption(password) + "' , '" + userName + "')";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteAdminAccount(Long id) throws SQLException {
        try {
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM konta_administratorow WHERE id = '" + id + "'";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String dataEncryption(String pw) throws Exception {

        SecretKeySpec secretKeySpec = new SecretKeySpec("kluczcvbnnmmjfds".getBytes(StandardCharsets.UTF_8),"AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        byte[] encryptedData = cipher.doFinal(pw.getBytes(StandardCharsets.UTF_8));
        String encodedText = Base64.getEncoder().encodeToString(encryptedData);

        return encodedText;
    }

    public String dataDecryption(String encryptedPassword) throws Exception {

        SecretKeySpec secretKeySpec = new SecretKeySpec("kluczcvbnnmmjfds".getBytes(StandardCharsets.UTF_8),"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedData = cipher.doFinal(decodedBytes);

        return new String(decryptedData,StandardCharsets.UTF_8);

    }

}

