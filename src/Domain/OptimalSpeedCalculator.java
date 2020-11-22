package Domain;

import Persistence.Database;
import Persistence.PdfWriter;
import Persistence.Testclass;

import java.util.ArrayList;
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

    public OptimalSpeedCalculator() {

    }


    public void noobMethod() {
        ArrayList<Double> noob = new ArrayList<>();

        Database database = new Database();
        database.insertHum(noob);
        database.insertStateTimes(noob);
        database.insertTemp(noob);
        database.insertBatch(noob);
    }

    public void returnOfTheNoob() {

        PdfWriter pdfWriter = new PdfWriter();
        pdfWriter.printer();
    }

}
