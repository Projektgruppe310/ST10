package Domain;

import Persistence.PdfWriter;
import Persistence.Testclass;

import java.util.HashMap;
import java.util.Scanner;

public class OptimalSpeedCalculator {

    //declaring attributes
    private String productType;
    private double totalProducts;
    private double defectiveProducts;
    private double machineSpeed;
    double defectiveProductsPercentage;

    public OptimalSpeedCalculator() { }

    private double calculatePilsner (double machineSpeed){
        return 0.76D * Math.pow(1.01, machineSpeed);
    }


    public double calculateDefectiveProducts()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide machine speed (products per minute");
        machineSpeed = Double.parseDouble(scanner.nextLine());

        System.out.println("provide product type");
        if((scanner.nextLine().equals("pilsner")))
        {
            defectiveProducts = calculatePilsner(machineSpeed);
        }
        else System.out.println("invalid product type");
        defectiveProductsPercentage = (defectiveProducts/1000)*100;

        System.out.println("defective products = " + ((int) defectiveProducts) + " of total amount = 1.000 ");
        System.out.println("defective products percentage = " + defectiveProductsPercentage);

        return defectiveProducts;
    }

    public OptimalSpeedCalculator(String productType)
    {
        HashMap<String, Integer> relation = new HashMap<>();
        this.productType = productType;
    }




    /*public void noobMethod() {
        HashMap noob = new HashMap();

        Database database = new Database();
        database.insertHum(noob);
        database.insertStateTimes(noob);
        database.insertTemp(noob);
        database.insertBatch(noob);
    }   */

    public void returnOfTheNoob() {

        PdfWriter pdfWriter = new PdfWriter();
        pdfWriter.printer();

        Testclass testclass = new Testclass();
        testclass.printer();
    }

}
