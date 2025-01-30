package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

	@FXML
    private Button userLoginButton;
	
	@FXML
    private Button adminLoginButton;

	@FXML
    private Button bankLoginButton;

    // Handle user and admin login button actions
    @FXML
    public void openUserLogin() {
    	Main main = Main.getInstance();
    	main.setPrimaryStage(Main.primaryStage);
        main.showUserLoginView();
    }

    @FXML
    public void openAdminLogin() {
    	Main main = Main.getInstance();
    	main.setPrimaryStage(Main.primaryStage);
        main.showAdminLoginView();
    }
    
    @FXML
    public void openBankLogin() {
    	Main main = Main.getInstance();
    	main.setPrimaryStage(Main.primaryStage);
        main.showBankLoginView();
    }
}
