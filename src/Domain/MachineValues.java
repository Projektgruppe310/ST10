package Domain;

import Persistence.Database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class MachineValues {

    // When you start the machine, the program needs to keep running, as it needs to log values every 10 seconds.
    // Can you give the user the possibility to stop / abort while the program is logging values?
    // Need to implement wait() notify(), but too complicated for now.


    public void machineStarted() {

        HashMap<String, String> machineValues = new HashMap<>();


        // Idea was to be able to interrupt the machine while it was running/logging
        /*MachineControl machineControl = new MachineControl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("You have the following options while the machine is running: ");
        System.out.println("1. Stop the machine.");
        System.out.println("2. Abort the machine.");
        System.out.println("3. Get current machine values.");*/

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        long startTime = System.currentTimeMillis();

        machineValues.put("Batch ID", Read.getCurrentBatchId());
        machineValues.put("Batch Date", dateFormat.format(date));

        // Quick solution for PSQLException about enum beers
        HashMap<String, String> productTypes = new HashMap<>();
        productTypes.put("0", "PILSNER");
        productTypes.put("1", "WHEAT");
        productTypes.put("2", "IPA");
        productTypes.put("3", "STOUT");
        productTypes.put("4", "ALE");
        productTypes.put("5", "ALCOHOL FREE");

        machineValues.put("Product Type", productTypes.get(Read.getCurrentProduct()));
        machineValues.put("Start Humidity", Read.getHumidity());
        machineValues.put("Start Temperature", Read.getTemperature());
        machineValues.put("Start Vibration", Read.getVibration());

        int counter = 0;

        while (!Read.getCurrentState().equals("17")) {

            while (Read.getCurrentState().equals("6")) {

                machineValues.put("Humidity " + counter + "0 seconds", Read.getHumidity());
                machineValues.put("Temperature " + counter + "0 seconds", Read.getTemperature());
                machineValues.put("Vibration " + counter + "0 seconds", Read.getVibration());
                counter++;

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            /*int userInput = scanner.nextInt();

            switch (userInput){
                case 1:{
                    machineControl.machineCntrlCmd(3);
                    return;
                }
                case 2:{
                    machineControl.machineCntrlCmd(4);
                    return;
                }
                case 3:{
                    Read.getAllValues();
                }
            }*/

            }
        }

        machineValues.put("End Humidity", Read.getHumidity());
        machineValues.put("End Temperature", Read.getTemperature());
        machineValues.put("End Vibration", Read.getVibration());
        machineValues.put("Acceptable Amount Produced", Read.getProductsProduced());
        machineValues.put("Defect Amount Produced", Read.getFailedProductsProduced());
        machineValues.put("Total Amount Produced", Read.getCurrentQuantity());
        machineValues.put("Batch Duration seconds", String.valueOf((System.currentTimeMillis() - startTime) / 1000F));

        // Prints HashMap with all the values
        System.out.println(machineValues);

        Database database = new Database();
        database.receiveData(machineValues);

    }



}
