package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {

    static Connection connection = null;

    public static Connection getConnection() {

        //Connecting to database
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Batches",
                    "postgres",
                    "ST09"
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
