# Transaction Management Application

## Project Overview
The **Transaction Management Application** is a JavaFX-based application designed to facilitate the secure and efficient handling of financial transactions for both users and administrators within a banking system. The application provides distinct functionalities for administrators, bank staff, and individual users, ensuring a comprehensive and user-friendly experience.

## Key Features

### 1. User Authentication
- Differentiated login interfaces for administrators, bank staff, and users.
- Secured login mechanism to protect sensitive information.

### 2. User Management
- User-specific transaction history tracking.
- Account creation and deletion are managed by administrators.
- Real-time updates to user details.

### 3. Transaction Tracking
- Comprehensive transaction history for each user.
- Detailed information, including Transaction ID, User ID, Amount, and Description.
- Real-time total balance calculation for each user.

### 4. Role-Specific Functionalities
- **Admin Module**:
  - Can access all user credentials.
  - Can enroll or disenroll users from the bank user database.
  - Can update user credentials.
  
- **Bank Staff Module**:
  - Access to transaction records and user information.
  - Ability to perform transactions on behalf of users.
  - Can update and delete user transactions.
  
- **User Module**:
  - View personal transaction history.
  - Deposit and withdraw functionality.
  - Real-time display of account balance.

### 5. Database Connectivity
- Integration with a MySQL database for persistent data storage.

### 6. Graphical User Interface (GUI)
- Responsive GUI layout using JavaFX for seamless navigation.

### 7. Code Structure
- Well-organized codebase with separation of concerns.
- Utilizes the Model-View-Controller (MVC) architecture for modular development.

### 8. Main Application Entry Point
- The `Main` class serves as the entry point for the application.
- Centralized management of different views and controllers.

## Project Structure

### Transaction Management Application 
│ 
### ├── application (Package) 
│ └── Main.java 
│ ├── controllers (Package) 
│ ├── AdminLoginController.java 
│ ├── AdminTransactionController.java 
│ ├── BankLoginController.java 
│ ├── BankTransactionController.java 
│ ├── MainController.java 
│ ├── UserLoginController.java 
│ └── UserTransactionController.java 
│ 
### ├── models (Package) 
│ ├── DBConnect.java 
│ ├── Transaction.java 
│ ├── TransactionModel.java 
│ ├── User.java 
│ └── UserModel.java 
│ 
### └── views (Package) 
├── AdminLoginView.fxml 
├── AdminTransactionView.fxml 
├── BankLoginView.fxml 
├── BankTransactionView.fxml 
├── MainView.fxml 
├── UserLoginView.fxml 
└── UserTransactionView.fxml


## Functionalities of Each File

### `application` Package:
- **Main.java**: Serves as the entry point for the application. It manages the main stage and initialization of the application.

### `controllers` Package:
- **AdminLoginController.java**: Handles user authentication for administrators and redirects to the admin view after validating credentials.
- **AdminTransactionController.java**: Manages user enrollment and disenrollment, and allows the administrator to update user credentials.
- **BankLoginController.java**: Manages user authentication for bank staff and redirects to the Bank transaction view after validating credentials.
- **BankTransactionController.java**: Facilitates access to transaction records and user information for bank staff. Also allows transactions on behalf of users and manages transaction updates.
- **MainController.java**: Central controller coordinating different views and managing navigation between admin, user, and bank login pages.
- **UserLoginController.java**: Manages user authentication for regular users, retrieves user credentials from the user database, and redirects to the User Transaction view.
- **UserTransactionController.java**: Displays transaction history, manages deposit/withdrawal functionalities, and shows real-time account balances for users.

### `models` Package:
- **DBConnect.java**: Manages the connection to the MySQL database and creates necessary tables.
- **Transaction.java**: Represents a financial transaction with fields like Transaction ID, User ID, Amount, and Description.
- **TransactionModel.java**: Handles CRUD operations related to transactions, calculates real-time total balances for users, and updates/retrieves transaction data.
- **User.java**: Represents a user with ID, username, and password credentials.
- **UserModel.java**: Manages user-specific functionalities such as account creation, deletion, and updates.

### `views` Package:
- **AdminLoginView.fxml**: Defines the layout for the admin login interface.
- **AdminTransactionView.fxml**: Defines the layout for the admin transaction management interface.
- **BankLoginView.fxml**: Defines the layout for the bank staff login interface.
- **BankTransactionView.fxml**: Defines the layout for the bank staff transaction management interface.
- **MainView.fxml**: Defines the layout for the main view, coordinating different functionalities.
- **UserLoginView.fxml**: Defines the layout for the regular user login interface.
- **UserTransactionView.fxml**: Defines the layout for the regular user transaction management interface.

## Database Integration
The application integrates with a MySQL database to store user and transaction data persistently. The connection is handled by `DBConnect.java`.

## Conclusion
The **Transaction Management Application** provides a reliable and secure platform for administrators, bank staff, and users to manage and track financial transactions effectively. The modular design ensures scalability and easy maintenance, enabling future enhancements and updates.
