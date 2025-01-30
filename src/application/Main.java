package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Main instance;
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public Main() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Main instance already created");
        }
    }

    // getInstance method to access the instance
    public static Main getInstance() {
        return instance;
    }

    // getPrimaryStage method
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        showMainView();
    }

    // setPrimaryStage method
    public void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    // Show view method
    private void showView(String title, String resourcePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            if (primaryStage == null) {
                primaryStage = new Stage();
            }

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods to show different views
    public void showMainView() {
    	
        showView("Main Page", "/views/MainView.fxml");
    }

    public void showUserLoginView() {
        showView("User Login Page", "/views/UserLoginView.fxml");
    }

    public void showAdminLoginView() {
        showView("Admin Login Page", "/views/AdminLoginView.fxml");
    }

    public void showUserTransactionView() {
        showView("User Transactions Data Page", "/views/UserTransactionView.fxml");
    }

    public void showAdminTransactionView() {
        showView("Admin Transactions Data Page", "/views/AdminTransactionView.fxml");
    }

    public void showBankLoginView() {
        showView("Bank Login Page", "/views/BankLoginView.fxml");
    }

    public void showBankTransactionView() {
        showView("Bank Transactions Data Page", "/views/BankTransactionView.fxml");
    }
}
