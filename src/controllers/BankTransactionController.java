package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Transaction;
import models.TransactionModel;

public class BankTransactionController {

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private Label totalTransactionsLabel;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField descriptionTextField;

    private TransactionModel transactionModel;

    public BankTransactionController() {
        this.transactionModel = new TransactionModel();
    }

    @FXML
    private void initialize() {
        // Initialize TableView and other components
        transactionModel.loadAllTransactions();
        transactionTable.setItems(transactionModel.getAllTransactions());
        totalTransactionsLabel.textProperty().bind(
                javafx.beans.binding.Bindings.format("%d", transactionModel.getAllTransactions().size())
        );
    }

    @FXML
    public void handleUpdateTransaction() {
        try {
            int id = Integer.parseInt(idTextField.getText());
            String username = usernameTextField.getText();
            double amount = Double.parseDouble(amountTextField.getText());
            String description = descriptionTextField.getText();

            // Perform the update in the model
            transactionModel.updateTransaction(id, username, amount, description);

            // Update the UI after transaction
            updateUI();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }
    
    @FXML
    public void handleDeleteTransaction() {
        // Get input values
        int id = Integer.parseInt(idTextField.getText());

        // Perform the delete in the model (e.g., remove from the database)
        transactionModel.deleteTransaction(id);

        // Update the UI after transaction
        updateUI();
    }

    private void updateUI() {
        // Update TableView and other UI components
        transactionTable.setItems(transactionModel.getAllTransactions());
        totalTransactionsLabel.textProperty().bind(
                javafx.beans.binding.Bindings.format("%d", transactionModel.getAllTransactions().size())
        );

        // Clear input fields after updating
        idTextField.clear();
        usernameTextField.clear();
        amountTextField.clear();
        descriptionTextField.clear();
    }

    @FXML
    public void handleLogout() {
        Main main = Main.getInstance();
        main.setPrimaryStage(Main.primaryStage);
        System.out.println("%n Bank Logged out Successfully %n");

        main.showMainView();
    }
}
