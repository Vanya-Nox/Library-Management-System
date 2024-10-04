/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import 
/**
 *
 * @author Dell
 */
public class DatabaseManager {
    
    private static final String URL = "jdbc:derby:libraryDB;create=true";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create Books table
            stmt.execute("CREATE TABLE Books (" +
                    "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                    "title VARCHAR(255)," +
                    "author VARCHAR(255)," +
                    "isbn VARCHAR(20)," +
                    "isAvailable BOOLEAN)");

            // Create Borrowers table
            stmt.execute("CREATE TABLE Borrowers (" +
                    "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                    "name VARCHAR(255)," +
                    "email VARCHAR(255)," +
                    "phoneNumber VARCHAR(20))");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
// CRUD operations for Books
    public static void addBook(Book book) {
        String sql = "INSERT INTO Books (title, author, isbn, isAvailable) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setBoolean(4, book.isAvailable());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn")
                );
                book.setAvailable(rs.getBoolean("isAvailable"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public static void updateBook(Book book) {
        String sql = "UPDATE Books SET title = ?, author = ?, isbn = ?, isAvailable = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setBoolean(4, book.isAvailable());
            pstmt.setInt(5, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // CRUD operations for Borrowers
    public static void addBorrower(Borrower borrower) {
        String sql = "INSERT INTO Borrowers (name, email, phoneNumber) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, borrower.getName());
            pstmt.setString(2, borrower.getEmail());
            pstmt.setString(3, borrower.getPhoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Borrower> getAllBorrowers() {
        List<Borrower> borrowers = new ArrayList<>();
        String sql = "SELECT * FROM Borrowers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Borrower borrower = new Borrower(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phoneNumber")
                );
                borrowers.add(borrower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowers;
    }
    
    public static void updateBorrower(Borrower borrower) {
        String sql = "UPDATE Borrowers SET name = ?, email = ?, phoneNumber = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, borrower.getName());
            pstmt.setString(2, borrower.getEmail());
            pstmt.setString(3, borrower.getPhoneNumber());
            pstmt.setInt(4, borrower.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBorrower(int id) {
        String sql = "DELETE FROM Borrowers WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


