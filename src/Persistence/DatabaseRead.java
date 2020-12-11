package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseRead {

    private static Connection connection = null;

    public HashMap<String, String> findBatch(String batchID){
        this.connection = DatabaseAccess.getConnection();

        HashMap<String, String> batchMap = new HashMap<>();

        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM batch WHERE real_batch_id = ?");
            selectStatement.setString(1, batchID);

            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()){
                batchMap.put("Batch ID", rs.getString("real_batch_id"));
                batchMap.put("Batch Date", rs.getString("batch_date"));
                batchMap.put("Product Type", rs.getString("product_type"));
                batchMap.put("Machine Speed", rs.getString("machine_speed"));
                batchMap.put("Acceptable Amount Produced", rs.getString("acceptable_amount_produced"));
                batchMap.put("Defect Amount Produced", rs.getString("defect_amount_produced"));
                batchMap.put("Total Amount Produced", rs.getString("total_amount_produced"));
                batchMap.put("Batch Duration seconds", rs.getString("batch_duration_seconds"));
                batchMap.put("OEE", rs.getString("oee"));
                batchMap.put("Avg Temperature", rs.getString("avg_temperature"));
                batchMap.put("Avg Humidity", rs.getString("avg_humidity"));
                batchMap.put("Avg Vibration", rs.getString("avg_vibration"));
            }

            return batchMap;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } return null;
    }
}
