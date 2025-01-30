package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Transaction;
import models.TransactionModel;
import javafx.collections.ObservableList;

public class UserTransactionController {

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private Label totalTransactionsLabel;

    @FXML
    private Label currentBalanceLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private ChoiceBox<String> operationChoiceBox;

    private TransactionModel transactionModel;

    public UserTransactionController() {
        this.transactionModel = new TransactionModel();
    }

    @FXML
    private void initialize() {
        UserLoginController userLoginController = new UserLoginController();
        String username = userLoginController.getUser();

        // Initialize TableView and other components
        ObservableList<Transaction> userTransactions = transactionModel.getUserTransactions(username);
        transactionTable.setItems(userTransactions);

        // Initialize current balance label
        currentBalanceLabel.textProperty().bind(
                javafx.beans.binding.Bindings.format("Current Balance: $%.2f", transactionModel.currentBalanceProperty(username))
        );

        // Initialize total transactions label
        totalTransactionsLabel.textProperty().bind(
                javafx.beans.binding.Bindings.format("Total Transactions: %d", userTransactions.size())
        );

        // Set choices for operationChoiceBox
        operationChoiceBox.setItems(FXCollections.observableArrayList("Deposit", "Withdraw"));
    }

    @FXML
    public void handleTransaction() {
        double amount = Double.parseDouble(amountTextField.getText());
        String operation = operationChoiceBox.getValue();

        UserLoginController userLoginController = new UserLoginController();
        String username = userLoginController.getUser();
        
        // Perform the transaction in the model (e.g., save to the database)
        transactionModel.addTransaction(username, amount, operation);

        // Update the UI after transaction
        updateUI(username);

        // Clear input fields after transaction
        amountTextField.clear();
        operationChoiceBox.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleLogout() {
        Main main = Main.getInstance();
        main.setPrimaryStage(Main.primaryStage);
    	System.out.printf("%n User Logged out Successfully %n");

        main.showMainView();
    }

    // Helper method to update the UI
    private void updateUI(String username) {
        ObservableList<Transaction> userTransactions = transactionModel.getUserTransactions(username);

        // Update TableView, current balance label, and total transactions label
        transactionTable.setItems(userTransactions);
        currentBalanceLabel.textProperty().bind(
                javafx.beans.binding.Bindings.format("Current Balance: $%.2f", transactionModel.currentBalanceProperty(username))
        );
        totalTransactionsLabel.textProperty().bind(
                javafx.beans.binding.Bindings.format("Total Transactions: %d", userTransactions.size())
        );
    }
}
