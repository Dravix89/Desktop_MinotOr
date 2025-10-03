package com.minotor.desktop_minotor.controller;

import com.minotor.desktop_minotor.service.ApiService;
import com.minotor.desktop_minotor.service.AuthService;


import java.io.IOException;
import java.time.Instant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    public LoginController() {
    }

    @FXML
    private void initialize() {
        this.loginButton.setOnAction((e) -> {
            String user = this.usernameField.getText();
            String pass = this.passwordField.getText();
            if (AuthService.login(user, pass)) {
                try {
                    ApiService.postVisit("login", Instant.now().toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                this.loadTrackingPage();
            } else {
                System.out.println("Identifiants Invalides");
            }

        });
    }

    private void loadTrackingPage() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/tracking.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = (Stage)this.loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
