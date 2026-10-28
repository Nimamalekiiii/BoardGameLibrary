package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/BoardGameLibraryDB";
    private static final String USER = "root";
    private static final String PASSWORD = lesPassord();

    // Passordet ligger i db.properties, som ikke lastes opp til GitHub
    private static String lesPassord() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        } catch (IOException e) {
            System.out.println("Fant ikke db.properties. Kopier db.properties.example og fyll inn passordet ditt.");
        }
        return props.getProperty("db.password", "");
    }

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Kunne ikke koble til databasen.");
            e.printStackTrace();
            return null;
        }

    }

}