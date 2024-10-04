/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Dell
 */
public class LibraryManagementSystem {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public LibraryManagementSystem() {
        initialize();
}
    
private void initialize() {
        mainFrame = new JFrame("Library Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createMainDashboard();
        createBooksManagement();
        createBorrowersManagement();

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void createMainDashboard() {
        JPanel dashboard = new JPanel();
        JButton booksButton = new JButton("Manage Books");
        JButton borrowersButton = new JButton("Manage Borrowers");

        booksButton.addActionListener(e -> cardLayout.show(mainPanel, "Books"));
        borrowersButton.addActionListener(e -> cardLayout.show(mainPanel, "Borrowers"));

        dashboard.add(booksButton);
        dashboard.add(borrowersButton);

        mainPanel.add(dashboard, "Dashboard");
    }

    private void createMainDashboard() {
        JPanel dashboard = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        dashboard.add(titleLabel, gbc);

        JButton booksButton = new JButton("Manage Books");
        booksButton.addActionListener(e -> cardLayout.show(mainPanel, "Books"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        dashboard.add(booksButton, gbc);

        JButton borrowersButton = new JButton("Manage Borrowers");
        borrowersButton.addActionListener(e -> cardLayout.show(mainPanel, "Borrowers"));
        gbc.gridx = 1;
        gbc.gridy = 1;
        dashboard.add(borrowersButton, gbc);

        mainPanel.add(dashboard, "Dashboard");
    }

    private void createBooksManagement() {
        BooksManagementPanel booksPanel = new BooksManagementPanel();
        
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(booksPanel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        wrapperPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(wrapperPanel, "Books");
    }

    private void createBorrowersManagement() {
        BorrowersManagementPanel borrowersPanel = new BorrowersManagementPanel();
        
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(borrowersPanel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        wrapperPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(wrapperPanel, "Borrowers");
    }
    
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseManager.initializeDatabase();
            new LibraryManagementSystem();
        });
    
