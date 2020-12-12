package Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class LiveMachineValues extends Thread {

    public LiveMachineValues(String currentProduct) {
        this.currentProduct = Integer.parseInt(currentProduct);
    }

    private int currentProduct;
    private ArrayList<String> tempArray = new ArrayList<>();
    private ArrayList<String> humidityArray = new ArrayList<>();
    private ArrayList<String> vibrationArray = new ArrayList<>();

    private HashMap<String, String> machineValues = new HashMap<>();

    @Override
    public void run() {

        int counter = 0;

        int defectProducts = Integer.parseInt(Read.getFailedProductsProduced());

        long startTime = System.currentTimeMillis();

        while (Read.getCurrentStateLive().equals("6")) {

            String temperature = Read.getTemperature();
            String humidity = Read.getHumidity();
            String vibration = Read.getVibration();

            int productsProduced = Integer.parseInt(Read.getProductsProduced());

            machineValues.put("Temperature " + counter + "0 seconds", temperature);
            machineValues.put("Humidity " + counter + "0 seconds", humidity);
            machineValues.put("Vibration " + counter + "0 seconds", vibration);

            tempArray.add(temperature);
            humidityArray.add(humidity);
            vibrationArray.add(vibration);

            double elapsedTime = (System.currentTimeMillis() - startTime) / 1000D;
            OEECalculator oeeCalculator = new OEECalculator(this.currentProduct, productsProduced, defectProducts, elapsedTime);
            System.out.println("The current OEE is: " + oeeCalculator.calculateLiveOEE());
            System.out.println("");

            counter++;


            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public ArrayList<String> getTempArray() {
        return tempArray;
    }

    public ArrayList<String> getHumidityArray() {
        return humidityArray;
    }

    public ArrayList<String> getVibrationArray() {
        return vibrationArray;
    }

    public HashMap<String, String> getMachineValues() {
        return machineValues;
    }
}
