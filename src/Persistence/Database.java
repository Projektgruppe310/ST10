package Persistence;

import java.sql.*;

public class Database {


    static Connection connection = null;

    public static void main(String[] args) {

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


        //Insert user into database
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO batch (batch_date, product_type, acceptable_amount_produced, defect_amount_produced, total_amount_produced) VALUES (2020-11-18,  ?)");

            //Insert data
            insertStatement.setString(1, "John Doe"); //"INSERT INTO users (name, cpr) VALUES ("John Doe", ?)"
            insertStatement.setString(2, "1111111111"); //"INSERT INTO users (name, cpr) VALUES ("John Doe", "1111111111")"

            //Insert to database
            insertStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Query database for users with CPR X
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM users WHERE cpr = ?");
            queryStatement.setString(1, "1111111111");

            //ResultSet = den data man får tilbage
            ResultSet queryResultSet = queryStatement.executeQuery();

            //Udskrevet til console
            System.out.println("The following user matched the query:");
            while(queryResultSet.next()) {
                System.out.println("The users name was " + queryResultSet.getString("name") + " and his CPR number was " + queryResultSet.getString("cpr"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
