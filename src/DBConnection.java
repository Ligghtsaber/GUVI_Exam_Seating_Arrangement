// DBConnection.java
// Provides a connection to MySQL Server

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // JDBC URL format: jdbc:mysql://host:port/database
            String url = "jdbc:mysql://localhost:3306/exam_seating";
            String username = "root"; // replace with your MySQL username
            String password = "asdfghjkl"; // replace with your MySQL password

            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Database connection failed:");
            e.printStackTrace();
            return null;
        }
    }
}
