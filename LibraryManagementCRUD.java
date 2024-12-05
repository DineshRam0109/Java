CREATE DATABASE LibraryDB;

USE LibraryDB;

CREATE TABLE books (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    available BOOLEAN
);

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LibraryManagementCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB";
    private static final String USER = "root";  
    private static final String PASSWORD = "password";  

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}



public class LibraryManagementSystem {

    // Create: Add a new book
    public static void addBook(String isbn, String title, String author, boolean available) {
        String query = "INSERT INTO books (isbn, title, author, available) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, isbn);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setBoolean(4, available);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    // Read: View all books
    public static void viewAllBooks() {
        String query = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String author = rs.getString("author");
                boolean available = rs.getBoolean("available");
                System.out.println(isbn + " | " + title + " | " + author + " | " + (available ? "Available" : "Not Available"));
            }
        } catch (SQLException e) {
            System.out.println("Error reading books: " + e.getMessage());
        }
    }

    // Update: Modify a book's availability status
    public static void updateBookAvailability(String isbn, boolean available) {
        String query = "UPDATE books SET available = ? WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setBoolean(1, available);
            pstmt.setString(2, isbn);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book availability updated.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    // Delete: Remove a book by ISBN
    public static void deleteBook(String isbn) {
        String query = "DELETE FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, isbn);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    // Main method to test CRUD operations
    public static void main(String[] args) {
        // Add a book
        addBook("1234567890", "Java Programming", "John Doe", true);

        // View all books
        System.out.println("All books:");
        viewAllBooks();

        // Update a book's availability
        updateBookAvailability("1234567890", false);

        // View updated list of books
        System.out.println("After updating availability:");
        viewAllBooks();

        // Delete a book
        deleteBook("1234567890");

        // View updated list of books after deletion
        System.out.println("After deleting a book:");
        viewAllBooks();

        // Attempt to delete a non-existing book
        deleteBook("1111111111");
    }
}