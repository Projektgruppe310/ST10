package Domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterruptMachine extends Thread{

    @Override
    public void run() {

        MachineControl machineControl = new MachineControl();
        String input = null;

        while (Read.getCurrentStateLive().equals("6")) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                while(true){
                    try {
                        if (bufferedReader.ready()) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            try {
                input = bufferedReader.readLine();
                System.out.println(input + "hejhejhej");
            } catch (IOException e) {
                e.printStackTrace();
            }


            switch (input) {
                case "1": {
                    machineControl.machineCntrlCmd(3);
                    break;
                }
                case "2": {
                    machineControl.machineCntrlCmd(4);
                    break;
                }
            }
        }
    }
}
