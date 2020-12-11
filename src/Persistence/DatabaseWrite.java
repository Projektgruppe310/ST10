package Persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DatabaseWrite {

    static Connection connection = null;

    public void receiveData(HashMap machineValues){

        ArrayList<Integer> possibleColumns = new ArrayList<>();
        possibleColumns.addAll(Arrays.asList( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 18, 24, 30, 36, 42, 48, 54, 60));

        insertHum(possibleColumns, machineValues);
        insertTemp(possibleColumns, machineValues);
        insertVibration(possibleColumns, machineValues);
        insertStateTimes();
        insertBatch(machineValues);
    }

    private boolean insertHum(ArrayList<Integer> possibleColumns, HashMap humValues) {

        try {
            this.connection = DatabaseAccess.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO humidities (col0000, col0010, col0020, col0030, col0040, col0050, col0060, col0070, col0080, col0090, col0100, col0110, col0120, col0180, col0240, col0300, col0360, col0420, col0480, col0540, col0600) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            int column = 1;

            for (int i: possibleColumns){

                if (humValues.containsKey("Humidity " + i + "0 seconds")){
                    insertStatement.setFloat(column, Float.parseFloat((String) humValues.get("Humidity " + i + "0 seconds")));
                }
                else {
                    insertStatement.setNull(column, Types.DOUBLE );
                }
                column++;
            }

            insertStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean insertStateTimes() { //HashMap stateTimes

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

    private boolean insertTemp(ArrayList<Integer> possibleColumns, HashMap tempValues) {

        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO temperatures (col0000, col0010, col0020, col0030, col0040, col0050, col0060, col0070, col0080, col0090, col0100, col0110, col0120, col0180, col0240, col0300, col0360, col0420, col0480, col0540, col0600) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            int column = 1;

            for (int i: possibleColumns){

                if (tempValues.containsKey("Temperature " + i + "0 seconds")){
                    insertStatement.setFloat(column, Float.parseFloat((String) tempValues.get("Temperature " + i + "0 seconds")));
                }
                else {
                    insertStatement.setNull(column, Types.DOUBLE );
                }
                column++;
            }

            insertStatement.execute();

            return true;



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean insertVibration(ArrayList<Integer> possibleColumns, HashMap vibrationValues) {

        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO vibrations (col0000, col0010, col0020, col0030, col0040, col0050, col0060, col0070, col0080, col0090, col0100, col0110, col0120, col0180, col0240, col0300, col0360, col0420, col0480, col0540, col0600) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            int column = 1;

            for (int i: possibleColumns){

                if (vibrationValues.containsKey("Vibration " + i + "0 seconds")){
                    insertStatement.setFloat(column, Float.parseFloat((String) vibrationValues.get("Vibration " + i + "0 seconds")));
                }
                else {
                    insertStatement.setNull(column, Types.DOUBLE );
                }
                column++;
            }

            insertStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean insertBatch(HashMap batchValues) {

        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO batch (batch_date, product_type, acceptable_amount_produced, defect_amount_produced, total_amount_produced, " +
                    "batch_duration_seconds, oee, avg_temperature, temperature_logs, avg_humidity, humidity_logs, avg_vibration, vibration_logs, " +
                    "time_in_states, real_batch_id, machine_speed) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            insertStatement.setString(1, (String) batchValues.get("Batch Date"));                        //"INSERT INTO batch (batch_date)                   VALUES ("2020-20-11")"
            insertStatement.setObject(2, batchValues.get("Product Type"), Types.OTHER);                       //"INSERT INTO batch (product_type)                 VALUES ("1")"      1=PILSNER,  2=WHEAT...('PILSNER', 'WHEAT', 'IPA', 'STOUT', 'ALE', 'ALCOHOL_FREE');
            insertStatement.setDouble(3, Double.parseDouble((String) batchValues.get("Acceptable Amount Produced")));                                          //"INSERT INTO batch (acceptable_amount_produced)   VALUES ("800")"
            insertStatement.setDouble(4, Double.parseDouble((String) batchValues.get("Defect Amount Produced")));                                          //"INSERT INTO batch (defect_amount_produced)       VALUES ("9004")"
            insertStatement.setDouble(5, Double.parseDouble((String) batchValues.get("Total Amount Produced")));                                         //"INSERT INTO batch (total_amount_produced)        VALUES ("9005")"
            insertStatement.setDouble(6, Double.parseDouble((String) batchValues.get("Batch Duration seconds")));
            insertStatement.setDouble(7, Double.parseDouble((String) batchValues.get("OEE")));
            insertStatement.setDouble(8, Double.parseDouble((String) batchValues.get("Avg Temperature")));
            insertStatement.setInt(9, 1);                                               //"INSERT INTO batch (temperature_logs)             VALUES ("1")"
            insertStatement.setDouble(10, Double.parseDouble((String) batchValues.get("Avg Humidity")));
            insertStatement.setInt(11, 1);                                               //"INSERT INTO batch (humidity_logs)                VALUES ("1")"
            insertStatement.setDouble(12, Double.parseDouble((String) batchValues.get("Avg Vibration")));
            insertStatement.setInt(13, 1);
            insertStatement.setInt(14, 1);                                               //"INSERT INTO batch (time_in_states)               VALUES ("1")"
            insertStatement.setString(15, (String) batchValues.get("Batch ID"));
            insertStatement.setDouble(16, Double.parseDouble((String) batchValues.get("Machine Speed")));

            //Insert to database
            insertStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

