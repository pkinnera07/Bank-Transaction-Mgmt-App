package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class BankLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void handleBankLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform admin login logic
        // Example: Check if username and password match admin credentials
        if ("bank".equals(username) && "bank123".equals(password)) {
        	System.out.printf("%n Bank '%s' Login Successful %n", username);

            // Navigate to AdminTransactionView.fxml
        	Main main = Main.getInstance();
        	main.setPrimaryStage(Main.primaryStage);

            main.showBankTransactionView();
        } else {
            // Show error message or handle unsuccessful login
            System.out.println("Bank login failed. Please check your credentials.");
        }
    }
}
