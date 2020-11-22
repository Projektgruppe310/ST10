package Persistence;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

public class Database {


    static Connection connection = null;




        //Insert user into database


    public boolean insertHum(ArrayList<Double> humValues) {


        try {
            this.connection = DatabaseAccess.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO humidities (col0010, col0020, col0030, col0040, col0050, col0100) VALUES (?,?,?,?,?,?)");


            //Insert data
            insertStatement.setFloat(1, 9001);   //"INSERT INTO humidities (col0010) VALUES ("9001")"      humvalues.get(0)
            insertStatement.setFloat(2, 9002);   //"INSERT INTO humidities (col0020) VALUES ("9002")"      humvalues.get(1)
            insertStatement.setFloat(3, 9003);   //"INSERT INTO humidities (col0030) VALUES ("9003")"      humvalues.get(2)
            insertStatement.setFloat(4, 9004);   //"INSERT INTO humidities (col0040) VALUES ("9004")"      humvalues.get(3)
            insertStatement.setFloat(5, 9005);   //"INSERT INTO humidities (col0050) VALUES ("9005")"      humvalues.get(4)
            insertStatement.setFloat(6, 9006);   //"INSERT INTO humidities (col0100) VALUES ("9006")"      humvalues.get(5)
            //Insert to database
            insertStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean insertStateTimes(ArrayList<Double> stateTimes) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO state_times (deactived, clearing, stopped, starting, idle, suspended, execute, stopping, aborting, aborted, resetting, completing, complete, deactivating, activating) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");


            //Insert data
            insertStatement.setFloat(1, 1);      //"INSERT INTO state_times (col0010) VALUES ("9001")"      stateTimes.get(0)
            insertStatement.setFloat(2, 2);      //"INSERT INTO state_times (col0020) VALUES ("9002")"      stateTimes.get(1)
            insertStatement.setFloat(3, 3);      //"INSERT INTO state_times (col0030) VALUES ("9003")"      stateTimes.get(2)
            insertStatement.setFloat(4, 4);      //"INSERT INTO state_times (col0040) VALUES ("9004")"      stateTimes.get(3)
            insertStatement.setFloat(5, 5);      //"INSERT INTO state_times (col0050) VALUES ("9005")"      stateTimes.get(4)
            insertStatement.setFloat(6, 6);      //"INSERT INTO state_times (col0100) VALUES ("9006")"      stateTimes.get(5)
            insertStatement.setFloat(7, 7);      //"INSERT INTO state_times (col0010) VALUES ("9001")"      stateTimes.get(6)
            insertStatement.setFloat(8, 8);      //"INSERT INTO state_times (col0020) VALUES ("9002")"      stateTimes.get(7)
            insertStatement.setFloat(9, 9);      //"INSERT INTO state_times (col0030) VALUES ("9003")"      stateTimes.get(8)
            insertStatement.setFloat(10, 10);    //"INSERT INTO state_times (col0040) VALUES ("9004")"      stateTimes.get(9)
            insertStatement.setFloat(11, 11);    //"INSERT INTO state_times (col0050) VALUES ("9005")"      stateTimes.get(10)
            insertStatement.setFloat(12, 12);    //"INSERT INTO state_times (col0100) VALUES ("9006")"      stateTimes.get(11)
            insertStatement.setFloat(13, 13);    //"INSERT INTO state_times (col0040) VALUES ("9004")"      stateTimes.get(12)
            insertStatement.setFloat(14, 14);    //"INSERT INTO state_times (col0050) VALUES ("9005")"      stateTimes.get(13)
            insertStatement.setFloat(15, 0);     //"INSERT INTO state_times (col0100) VALUES ("9006")"      stateTimes.get(14)
            //Insert to database
            insertStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean insertTemp(ArrayList<Double> tempValues) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO temperatures (col0010, col0020, col0030, col0040, col0050, col0100) VALUES (?,?,?,?,?,?)");


            //Insert data
            insertStatement.setFloat(1, (float) 55.3);      //"INSERT INTO temperatures (col0010) VALUES ("9001")"      tempValues.get(0)
            insertStatement.setFloat(2, (float) 66.3);      //"INSERT INTO temperatures (col0020) VALUES ("9002")"      tempValues.get(1)
            insertStatement.setFloat(3, (float) 69.69);     //"INSERT INTO temperatures (col0030) VALUES ("9003")"      tempValues.get(2)
            insertStatement.setFloat(4, (float) 13.37);     //"INSERT INTO temperatures (col0040) VALUES ("9004")"      tempValues.get(3)
            insertStatement.setFloat(5, (float) 80.113);    //"INSERT INTO temperatures (col0050) VALUES ("9005")"      tempValues.get(4)
            insertStatement.setDouble(6, 4.20);          //"INSERT INTO temperatures (col0100) VALUES ("9006")"      tempValues.get(5)
            //Insert to database
            insertStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean insertBatch(ArrayList<Double> tempValues) {


        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO batch (batch_date, product_type, acceptable_amount_produced, defect_amount_produced, total_amount_produced, temperature_logs, humidity_logs, time_in_states) VALUES (?,?,?,?,?,?,?,?)");
            //Insert data
            insertStatement.setDate(1, Date.valueOf("2020-11-20"));                        //"INSERT INTO batch (batch_date)                   VALUES ("2020-20-11")"
            insertStatement.setObject(2, "PILSNER", Types.OTHER);                       //"INSERT INTO batch (product_type)                 VALUES ("1")"      1=PILSNER,  2=WHEAT...('PILSNER', 'WHEAT', 'IPA', 'STOUT', 'ALE', 'ALCOHOL_FREE');
            insertStatement.setDouble(3, 800);                                          //"INSERT INTO batch (acceptable_amount_produced)   VALUES ("800")"
            insertStatement.setDouble(4, 200);                                          //"INSERT INTO batch (defect_amount_produced)       VALUES ("9004")"
            insertStatement.setDouble(5, 1000);                                         //"INSERT INTO batch (total_amount_produced)        VALUES ("9005")"
            insertStatement.setInt(6, 1);                                               //"INSERT INTO batch (temperature_logs)             VALUES ("1")"
            insertStatement.setInt(8, 1);                                               //"INSERT INTO batch (humidity_logs)                VALUES ("1")"
            insertStatement.setInt(7, 1);                                               //"INSERT INTO batch (time_in_states)               VALUES ("1")"

            //Insert to database
            insertStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    //Query database for users with CPR X

   // public void nestedQuery() {
   //     try {
     //       PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM batch WHERE batch_id = ?");
      //      queryStatement.setString(1, "1");

            //ResultSet = den data man får tilbage
      //      ResultSet queryResultSet = queryStatement.executeQuery();

            //Udskrevet til console
        //    System.out.println("The following user matched the query:");
          //  while (queryResultSet.next()) {
       //         System.out.println("The users name was " + queryResultSet.getString("name") + " and his CPR number was " + queryResultSet.getString("cpr"));

    //        }

     //   } catch (SQLException throwables) {
      //      throwables.printStackTrace();
      //  }
   // }
}

