package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:data.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try {
            connection = Database.getConnection();
            System.out.println("Connection established successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        Statement createUserTable = null;
        try {
            createUserTable = getConnection().createStatement();
            createUserTable.execute("CREATE TABLE IF NOT EXISTS todo (id INTEGER, task TEXT NOT NULL STRICT, PRIMARY KEY(id))");
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}