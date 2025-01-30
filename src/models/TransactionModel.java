package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.UserLoginController;

public class TransactionModel {

    private static final String SELECT_ALL_TRANSACTIONS = "SELECT * FROM PP_Transactions";
    private static final String SELECT_USER_TRANSACTIONS = "SELECT * FROM PP_Transactions WHERE username = ?";
    private static final String INSERT_TRANSACTION = "INSERT INTO PP_Transactions (amount, description, username) VALUES (?, ?, ?)";
    private static final String GET_TOTAL_AMOUNT = "SELECT SUM(CASE WHEN description = 'deposit' THEN amount ELSE -amount END) FROM PP_Transactions WHERE username = ?";
    private static final String UPDATE_TRANSACTION = "UPDATE PP_Transactions SET amount = ?, description = ? WHERE id = ? AND username = ?";
    private static final String DELETE_TRANSACTION = "DELETE FROM PP_Transactions WHERE id=?";

    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    private final DoubleProperty currentBalance = new SimpleDoubleProperty();

    public TransactionModel() {
        DBConnect.createTables();
        UserLoginController userLoginController = new UserLoginController();
        String username = userLoginController.getUser();
        loadUserTransactionsFromDatabase(username);
        updateCurrentBalance(username);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return transactions;
    }

    public ObservableList<Transaction> getUserTransactions(String username) {
        return transactions;
    }

    public double getCurrentBalance() {
        return currentBalance.get();
    }

    public DoubleProperty currentBalanceProperty(String username) {
        return currentBalance;
    }

    public void addTransaction(String username, double amount, String description) {
        double adjustedAmount = amount;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION)) {

            preparedStatement.setDouble(1, adjustedAmount);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();

            // Update the model and UI
            loadUserTransactionsFromDatabase(username);
            updateCurrentBalance(username);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTransaction(int id, String username, double amount, String description) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRANSACTION)) {

            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, username);
            preparedStatement.executeUpdate();

            // Update the model and UI
            loadAllTransactions();
            updateCurrentBalance(username);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteTransaction(int id) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRANSACTION)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            // Update the model and UI
            loadAllTransactions();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void loadAllTransactions() {
        transactions.clear(); // Clear the list before populating it again

        try (Connection connection = DBConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_TRANSACTIONS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                String username = resultSet.getString("username");
                transactions.add(new Transaction(id, amount, description, username));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUserTransactionsFromDatabase(String username) {
        transactions.clear(); // Clear the list before populating it again

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_TRANSACTIONS)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    double amount = resultSet.getDouble("amount");
                    String description = resultSet.getString("description");
                    String resultUsername = resultSet.getString("username");
                    transactions.add(new Transaction(id, amount, description, resultUsername));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCurrentBalance(String username) {
        double totalAmount = 0;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TOTAL_AMOUNT)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    totalAmount = resultSet.getDouble(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        currentBalance.set(totalAmount);
    }
}
