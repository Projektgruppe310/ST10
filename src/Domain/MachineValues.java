package Domain;

import Persistence.DatabaseWrite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MachineValues {

    // When you start the machine, the program needs to keep running, as it needs to log values every 10 seconds.
    // Can you give the user the possibility to stop / abort while the program is logging values?
    // Need to implement wait() notify(), but too complicated for now.


    public void machineStarted() {

        HashMap<String, String> machineValues = new HashMap<>();
        ArrayList<String> tempArray = new ArrayList<>();
        ArrayList<String> humidityArray = new ArrayList<>();
        ArrayList<String> vibrationArray = new ArrayList<>();


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
        //machineValues.put("Start Humidity", Read.getHumidity());
        //machineValues.put("Start Temperature", Read.getTemperature());
        //machineValues.put("Start Vibration", Read.getVibration());

        int counter = 0;

        while (!Read.getCurrentState().equals("17")) {

            while (Read.getCurrentState().equals("6")) {

                String temperature = Read.getTemperature();
                String humidity = Read.getHumidity();
                String vibration = Read.getVibration();

                machineValues.put("Temperature " + counter + "0 seconds", temperature);
                machineValues.put("Humidity " + counter + "0 seconds", humidity);
                machineValues.put("Vibration " + counter + "0 seconds", vibration);

                tempArray.add(temperature);
                humidityArray.add(humidity);
                vibrationArray.add(vibration);

                OEECalculator oeeCalculator = new OEECalculator(Integer.parseInt(currentProduct), Integer.parseInt(Read.getProductsProduced()),
                        Integer.parseInt(Read.getFailedProductsProduced()), (int)(System.currentTimeMillis() - startTime));
                System.out.println("The current OEE is: " + oeeCalculator.calculateLiveOEE());
                System.out.println("");

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

        // Total amount, acceptable amount confusing in the simulation, as one of them does not run.

        String totalAmount = Read.getProductsProduced();
        String defectAmount = Read.getFailedProductsProduced();
        Double batchDurationSeconds = (System.currentTimeMillis() - startTime) / 1000D;

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
