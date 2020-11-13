package Domain;

import java.util.HashMap;

public class OptimalSpeedCalculator {
    //declaring attributes
    private String productType;
    private double totalProducts;
    private double defectiveProducts;
    private double acceptableProducts;
    private double curMachSpeed;

    public OptimalSpeedCalculator(String productType)
    {
        HashMap<String, Integer> relation = new HashMap<>();
        this.productType = productType;
    }
    OptimalSpeedCalculator pilsner = new OptimalSpeedCalculator("Pilsner");


    public OptimalSpeedCalculator() {

    }
}
