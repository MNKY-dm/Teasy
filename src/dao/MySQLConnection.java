package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLConnection {
    private static Connection connection;

    private static Connection connect() throws SQLException {

        try {
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var user = DatabaseConfig.getDbUsername();
            var password = DatabaseConfig.getDbPassword();

            // Uncomment if code above doesn't work)
            // var jdbcUrl = "jdbc:mysql://localhost:DATABASE_PORT/DATABASE_NAME";
            // var user = "USERNAME";
            // var password = "PASSWORD";

            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static Connection getConnection() throws SQLException {
        if (MySQLConnection.connection== null) {
            MySQLConnection.connection = MySQLConnection.connect();
        }
        return MySQLConnection.connection;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = MySQLConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Connexion OK!");
        }
    }
}
