package Domain;

import org.apache.log4j.varia.NullAppender;

public class Main{

    public static void main(String[] args) {


        MachineControl machineControl = new MachineControl();
        machineControl.machineSwitch(2);
    }
    
}