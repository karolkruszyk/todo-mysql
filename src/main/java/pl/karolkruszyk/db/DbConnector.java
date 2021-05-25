package pl.karolkruszyk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/todo-db";
    private static final String USERNAME = "karol";
    private static final String PASSWORD = "123456789";


    public static Connection connect() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }
}
