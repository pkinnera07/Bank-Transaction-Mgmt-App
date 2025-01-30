package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

    private static final String url = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
    private static final String username = "db510";
    private static final String password = "510";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void createTables() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            // Create User table
            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS PP_User ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL"
                    + ")";
            statement.executeUpdate(createUserTableQuery);

            // Add more tables as needed
            // Create Transaction table
            String createTransactionTableQuery = "CREATE TABLE IF NOT EXISTS PP_Transactions ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "amount DOUBLE NOT NULL,"
                    + "description VARCHAR(255) NOT NULL,"
                    + "username VARCHAR(255) NOT NULL"
                    + ")";
            statement.executeUpdate(createTransactionTableQuery);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addColumnToTransactionsTable(String columnName, String columnType) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String alterTableQuery = "ALTER TABLE PP_Transactions ADD COLUMN " + columnName + " " + columnType;
            statement.executeUpdate(alterTableQuery);

            System.out.println("Column added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void showTables() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SHOW TABLES")) {

            System.out.println("Tables in the database:");
            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                System.out.println(tableName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Add this method to retrieve transactions for a given username
    public static ResultSet getTransactionsForUser(String username) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PP_Transactions WHERE username = ?")) {

            preparedStatement.setString(1, username);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
