package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
	
    private static final String SELECT_ALL_USERS = "SELECT * FROM PP_User";
    private static final String INSERT_USER = "INSERT INTO PP_User (username, password) VALUES (?, ?)";
    private static final String DELETE_USER ="DELETE FROM PP_User WHERE username = ?";
    private static final String FIND_USER ="SELECT COUNT(*) FROM PP_User WHERE username = ? AND password = ?";

	private final ObservableList<User> users = FXCollections.observableArrayList();
    
    public UserModel() {
        DBConnect.createTables();
        loadUsersFromDatabase();
    }
    
    public ObservableList<User> getAllUsers() {
		return users;
	}
    
    public void addUser(String username, String password) {
    	
    	try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

               preparedStatement.setString(1, username);
               preparedStatement.setString(2, password);
               preparedStatement.executeUpdate();

    	} catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
    	
    	try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

               preparedStatement.setString(1, username);
               preparedStatement.executeUpdate();
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private void loadUsersFromDatabase() {
        users.clear(); // Clear the list before populating it again

        try (Connection connection = DBConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {

            while (resultSet.next()) {
            	
                String username = resultSet.getString("username");                
                String password = resultSet.getString("password");
                users.add(new User(username, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean validateUser(String username, String password) {
    	
    	try (Connection connection = DBConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

    		
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count > 0, username exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's requirements
        }

        return false; // Something went wrong or username doesn't exist
    }
 

}
