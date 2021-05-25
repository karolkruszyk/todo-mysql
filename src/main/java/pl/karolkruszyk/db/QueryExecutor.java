package pl.karolkruszyk.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {

    public static ResultSet executeSelect(String selectQuery) {
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    public static void executeQuery(String query) {
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
