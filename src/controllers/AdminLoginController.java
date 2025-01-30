package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminLoginController {
	@FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void handleAdminLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform admin login logic
        // Example: Check if username and password match admin credentials
        if ("admin".equals(username) && "admin123".equals(password)) {
        	System.out.printf("%n Admin Login Successful %n");

            // Navigate to AdminTransactionView.fxml
        	Main main = Main.getInstance();
        	main.setPrimaryStage(Main.primaryStage);

            main.showAdminTransactionView();
        } else {
            // Show error message or handle unsuccessful login
            System.out.println("Admin login failed. Please check your credentials.");
        }
    }
}
