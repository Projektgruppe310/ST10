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
                break;
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

        MachineControl machineControl = new MachineControl();
        machineControl.machineCntrlCmd(control);

        if (control == 2){
            MachineValues machineValues = new MachineValues();
            machineValues.machineStarted();
        }

    }

    private void oeeCalculator() {
        //Needs to include values needed for constructor.

        //TEST
        OEECalculator oee = new OEECalculator(1, 1000, 20);
        System.out.println("The machine's OEE is: " + oee.calculateOEE() + " for a total count of 1000, rejected count of 20, shift length of 8 hours with one 30 minute break.");

    }

    private void optimalSpeed() {
        // Needs to print out error for invalid commands

        OptimalSpeedCalculator noob = new OptimalSpeedCalculator("1");
        //noob.noobMethod();
        noob.returnOfTheNoob();

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
