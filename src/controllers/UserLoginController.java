package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.UserModel;

public class UserLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

	private String User = "user_001";

    public UserLoginController() {
        // No need for additional initialization in the constructor
    }


    
    @FXML
    private void handleUserLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        setUser(username);
        if (UserModel.validateUser(username, password)) {
            // Navigate to UserTransactionView.fxml
            Main main = Main.getInstance();
            main.setPrimaryStage(Main.primaryStage);

        	System.out.printf("%n User '%s' Login Successful %n", username);

            // Show UserTransactionView
            main.showUserTransactionView();
        } else {
            System.out.println("User login failed. Please check your credentials.");
        }
    }
    

    public void setUser(String username) {
        this.User = username;
    }
    
    public String getUser() {
        return this.User;
    }



    

}
