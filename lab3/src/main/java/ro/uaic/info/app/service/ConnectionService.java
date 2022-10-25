package ro.uaic.info.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
    private static final String CONN_DRIVER = "org.postgresql.Driver";
    private static final String URL_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static ConnectionService instance = null;
    private static Connection connection = null;

    ConnectionService() {
        try {
            Class.forName(CONN_DRIVER);
            connection = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (instance == null)
            instance = new ConnectionService();

        return connection;
    }

    public static void close() {
        if (instance != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
