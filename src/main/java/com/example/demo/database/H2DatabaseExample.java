package com.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2DatabaseExample {
    public static void main(String[] args) {
        // JDBC URL for en in-memory database
        String url = "jdbc:h2:mem:testdb";  // "testdb" er navnet på databasen

        try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
            // Opprette en SQL-setning
            Statement stmt = conn.createStatement();
            
            // SQL for å opprette tabell med 3 kolonner
            String createTableSQL = "CREATE TABLE user_info (" +
                                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                                     "email VARCHAR(255) NOT NULL," +
                                     "type VARCHAR(50)" +
                                     ");";

            // Utføre SQL-setningen
            stmt.execute(createTableSQL);
            
            System.out.println("Tabell opprettet.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
