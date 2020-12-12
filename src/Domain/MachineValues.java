package Domain;

import Persistence.DatabaseWrite;
import java.text.SimpleDateFormat;
import java.util.*;

public class MachineValues{

    // When you start the machine, the program needs to keep running, as it needs to log values every 10 seconds.
    // Can you give the user the possibility to stop / abort while the program is logging values?
    // Need to implement wait() notify(), but too complicated for now.
    // Total amount, acceptable amount confusing in the simulation, as one of them does not run.

    public void machineStarted() {

        HashMap<String, String> machineValues = new HashMap<>();
        ArrayList<String> tempArray = new ArrayList<>();
        ArrayList<String> humidityArray = new ArrayList<>();
        ArrayList<String> vibrationArray = new ArrayList<>();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        long startTime = System.currentTimeMillis();

        machineValues.put("Batch ID", Read.getCurrentBatchId());
        machineValues.put("Batch Date", dateFormat.format(date));
        machineValues.put("Machine Speed", Read.getMachineSpeed());

        // Quick solution for PSQLException about enum beers
        HashMap<String, String> productTypes = new HashMap<>();
        productTypes.put("0", "PILSNER");
        productTypes.put("1", "WHEAT");
        productTypes.put("2", "IPA");
        productTypes.put("3", "STOUT");
        productTypes.put("4", "ALE");
        productTypes.put("5", "ALCOHOL FREE");

        String currentProduct = Read.getCurrentProduct(); //used several times
        machineValues.put("Product Type", productTypes.get(currentProduct));

        //Starting thread for live machine values
        LiveMachineValues liveMachineValues = new LiveMachineValues(currentProduct);
        Thread liveValuesThread = new Thread(liveMachineValues);
        liveValuesThread.start();

        /*try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // Problem implementing this, as system.in kept blocking the system?
        //Print user information and start thread so user can interrupt the machine
        /*System.out.println("You have the following options while the machine is running: ");
        System.out.println("1. Stop the machine.");
        System.out.println("2. Abort the machine.");

        InterruptMachine interruptMachine = new InterruptMachine();
        Thread interruptThread = new Thread(interruptMachine);*/


        // Create HashSet to make it easier to compare several values. Inspiration from hjmd's answer in StackOverflow:
        // https://stackoverflow.com/questions/10205437/compare-one-string-with-multiple-values-in-one-expression
        Set<String> states = new HashSet<>();
        states.add("17");   // Completed
        states.add("11");   // Held
        states.add("9");    // Aborted
        states.add("4");    // Idle
        states.add("2");    // Stopped

        while (!states.contains(Read.getCurrentStateLive())) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Double batchDurationSeconds = (System.currentTimeMillis() - startTime) / 1000D;

        try {
            liveValuesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tempArray = liveMachineValues.getTempArray();
        humidityArray = liveMachineValues.getHumidityArray();
        vibrationArray = liveMachineValues.getVibrationArray();
        machineValues.putAll(liveMachineValues.getMachineValues());

        String totalAmount = Read.getProductsProduced();
        String defectAmount = Read.getFailedProductsProduced();

        machineValues.put("End Humidity", Read.getHumidity());
        machineValues.put("End Temperature", Read.getTemperature());
        machineValues.put("End Vibration", Read.getVibration());
        machineValues.put("Acceptable Amount Produced", totalAmount);
        machineValues.put("Defect Amount Produced", defectAmount);
        machineValues.put("Total Amount Produced", Read.getCurrentQuantity());
        machineValues.put("Batch Duration seconds", String.valueOf(batchDurationSeconds));

        OEECalculator batchOEE = new OEECalculator(Integer.parseInt(currentProduct), Integer.parseInt(totalAmount),
                Integer.parseInt(defectAmount), batchDurationSeconds);
        machineValues.put("OEE", String.valueOf(batchOEE.calculateLiveOEE()));
        machineValues.put("Avg Temperature", temperatureAvg(tempArray));
        machineValues.put("Avg Humidity", humidityAvg(humidityArray));
        machineValues.put("Avg Vibration", vibrationAvg(vibrationArray));

        // Prints HashMap with all the values
        System.out.println(machineValues);

        DatabaseWrite databaseWrite = new DatabaseWrite();
        databaseWrite.receiveData(machineValues);


    }

    private String temperatureAvg (ArrayList<String> tempArray){
        double sum = 0;
        for (String temp: tempArray){
            sum =+ Double.parseDouble(temp);
        }

        return String.valueOf(sum / tempArray.size());
    }

    private String humidityAvg (ArrayList<String> humidityArray){
        double sum = 0;
        for (String humidity: humidityArray){
            sum =+ Double.parseDouble(humidity);
        }

        return String.valueOf(sum / humidityArray.size());
    }

    private String vibrationAvg (ArrayList<String> vibrationArray){
        double sum = 0;
        for (String vibration: vibrationArray){
            sum =+ Double.parseDouble(vibration);
        }

        return String.valueOf(sum / vibrationArray.size());
    }



}
