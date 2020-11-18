package Domain;

import java.util.HashMap;
import java.util.Scanner;

public class ControlHub {

    protected void cliCommands() {

        System.out.println("Please choose one of the following options (type the corresponding number): ");
        System.out.println("1. Read current machine values.");
        System.out.println("2. Create batch (customizable)");
        System.out.println("3. Control the machine (Start/Stop/Etc.)");
        System.out.println("4. Show OEE.");
        System.out.println("5. Show optimal speed.");
        System.out.println("6. Exit program.");

        Scanner s = new Scanner(System.in);

        int cmd = s.nextInt();



        switch (cmd){
            case 1:{
                readValues();
                break;
            }
            case 2:{
                Write write = new Write();
                write.createBatch();
                break;
            }
            case 3:{
                machineControl();
                break;
            }
            case 4:{
                oeeCalculator();
                break;
            }
            case 5:{
                optimalSpeed();
            }
            case 6:{
                s.close();
                System.exit(0);
            }
            default:{
                System.out.println("Invalid input. Try again.");
            }
        }

        cliCommands();

    }

    private void readValues() {
        Read.getAllValues();

    }

    private void machineControl() {
        System.out.println("You have the following options: ");
        System.out.println("0. ?"); //What does 0 do?
        System.out.println("1. Reset the machine.");
        System.out.println("2. Start the machine.");
        System.out.println("3. Stop the machine.");
        System.out.println("4. Abort the machine.");
        System.out.println("5. Clear the machine.");

        Scanner sc = new Scanner(System.in);
        int control = sc.nextInt();
        sc.close();

        MachineControl machineControl = new MachineControl();
        machineControl.machineCntrlCmd(control);

    }

    private void oeeCalculator() {
        //Needs to include values needed for constructor.

        //OEECalculator oee = new OEECalculator();
        //System.out.println("The machine's OEE is: " + oee.calculateOEE());

    }

    private void optimalSpeed() {
        // Needs to print out error for invalid commands

        System.out.println("Which beer needs to be optimized (0-5)?");
        System.out.println("0 - Pilsner");
        System.out.println("1 - Wheat");
        System.out.println("2 - IPA");
        System.out.println("3 - Stout");
        System.out.println("4 - Ale");
        System.out.println("5 - Alcohol Free");

        Scanner scanner = new Scanner(System.in);
        int beer = scanner.nextInt();


        HashMap beerMap = new HashMap();
        beerMap.put(0, "Pisner");
        beerMap.put(1, "Wheat");
        beerMap.put(2, "IPA");
        beerMap.put(3, "Stout");
        beerMap.put(4, "Ale");
        beerMap.put(5, "Alcohol Free");

        if (beerMap.get(beer) != null){
            OptimalSpeedCalculator osc = new OptimalSpeedCalculator((String) beerMap.get(beer));
            System.out.println("The optimal speed of the machine for " + beerMap.get(beer) + " beer is: "
            ); // Add optimal speed method
        }
        else {
            System.out.println("Invalid input.");
        }
        scanner.close();
    }

}