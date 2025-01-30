package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.User;
import models.UserModel;

public class AdminTransactionController {

	@FXML
    private TableView<User> userTable;
	
	@FXML
    private TextField usernameTextField;
	
	@FXML
    private TextField passwordTextField;
	
	@FXML
    private TextField userTextField;

	private UserModel userModel;
	
	 public AdminTransactionController() {
	        this.userModel = new UserModel();
	    }
	
	    @FXML
	    private void initialize() {
	        // Initialize TableView and other components
	        refreshUserTable();
	    }

	    private void refreshUserTable() {
	        userTable.setItems(userModel.getAllUsers());
	    }
	 
	@FXML
    public void handleEnroll() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        
        userModel.addUser(username, password);
        usernameTextField.clear();
        passwordTextField.clear();
        refreshUserTable();
        System.out.printf("%n User '%s' added successfully to the database %n", username);
	}
	
	@FXML
    public void handleDeEnroll() {
        String username = userTextField.getText();
        
        userModel.deleteUser(username);
        userTextField.clear();
        refreshUserTable();
        System.out.printf("%n User '%s' deleted successfully from the database %n", username);

        
	}
	
	 @FXML
	    public void handleLogout() {
	    	Main main = Main.getInstance();
	    	main.setPrimaryStage(Main.primaryStage);
        	System.out.printf("%n Admin Logged out Successfully %n");


	        main.showMainView();
	    }
}
