package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUDApplicationStudent {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // Change to your DB URL
    private static final String USER = "postgres"; // Change to your username
    private static final String PASSWORD = "123456"; // Change to your password

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE - Add new user
    public static void createUser(int id, String username, String email, String password) {
        String sql = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // READ - Retrieve user information
    public static void readUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
                // Avoid showing password for security reasons
            } else {
                System.out.println("User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error reading user: " + e.getMessage());
        }
    }

    // UPDATE - Modify user details
    public static void updateUser(int id, String username, String email, String password) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    // DELETE - Remove a user from the database
    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    // Main function to interact with the user
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nUser Management Menu:");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Create User
                    System.out.print("Enter user ID: ");
                    int createId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    createUser(createId, username, email, password);
                    break;

                case 2: // Read User
                    System.out.print("Enter user ID to read: ");
                    int readId = scanner.nextInt();
                    readUser(readId);
                    break;

                case 3: // Update User
                    System.out.print("Enter user ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    updateUser(updateId, newUsername, newEmail, newPassword);
                    break;

                case 4: // Delete User
                    System.out.print("Enter user ID to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteUser(deleteId);
                    break;

                case 5: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}