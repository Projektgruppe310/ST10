package Domain;

import org.apache.log4j.varia.NullAppender;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Read {

    /*skal forestille at være en metode der henter en specifik variabels værdi, metoden skal overloades, følgende
    er en liste af getter metoder til at hente disse*/
    public static void getMachineSpeed() { readValue(6, "::Program:Cube.Status.MachSpeed", "machine speed" ); }
    public static void getCurrentProduct() { readValue(6, "::Program:Cube.Admin.Parameter[0].Value", "current product" ); }
    public static void getCurrentBatchId() { readValue(6, "::Program:Cube.Status.Parameter[0].Value", "current batch id"); }
    public static void getCurrentQuantity() { readValue(6, "::Program:Cube.Status.Parameter[1].Value", "current quantity" ); }
    public static void getProductsProduced(){ readValue(6, "::Program:Cube.Admin.ProdProcessedCount", "products produced"); }
    public static void getFailedProductsProduced(){ readValue(6, "::Program:Cube.Admin.ProdDefectiveCount", "defective products produced"); }
    public static void getStopReasonId(){
        readValue(6, "::Program:Cube.Admin.StopReason.ID", "stop reason id");
    }
    public static void getVibration(){
        readValue(6, "::Program:Cube.Status.Parameter[4].Value", "vibration");
    }
    public static void getHumidity(){
        readValue(6, "::Program:Cube.Status.Parameter[2].Value", "relative humidity");
    }
    public static void getTemperature(){
        readValue(6,"::Program:Cube.Status.Parameter[3].Value", "temperature");
    }

    public static void getValues(){
        getVibration();
        getHumidity();
        getTemperature();
        getMachineSpeed();
        getCurrentQuantity();
        getCurrentProduct();
        getCurrentBatchId();
        getFailedProductsProduced();
        getProductsProduced();
    }

    private static OpcUaClient client = null;

    public static void readValue(int namespaceindex, String identifier, String parameterName) {
        //At first we try to establish a connection to the software simulator url, if it connects we want it to print all endpoints
        client = OpcUAConnector.getOpcUaClient();

            /* to read the value from a specific node, we can provide the namespace index as
            the first parameter, and the identifier string as the second parameter for the
            specific nodeId. Here we want to read the value corresponding to the cube.command.Machspeed */
            NodeId nodeId = new NodeId(namespaceindex, identifier);
        DataValue dataValue = null;
        try {
            dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

            /* now to simplify the response from the read operation, we want to store the read value that goes by
            "variant" as the variable specific to the operation. Here we start out by doing it for the machine speed
             */
            Variant variant = dataValue.getValue();

            //the following is what is to be printed out, dependent on the parameterName given
            switch (parameterName){
                case "machine speed":
                    float machineSpeed = (float) variant.getValue();
                    System.out.println("Machine speed = " + machineSpeed);
                    break;
                case "current product":
                    float currentProductFloat = (float) variant.getValue();
                    int currentProductInt = (int) currentProductFloat;
                    System.out.println("Current product = " + currentProductInt);
                    break;
                case "current batch id":
                    float currentBatchIdFloat =(float) variant.getValue();
                    int currentBatchIdInt = (int) currentBatchIdFloat;
                    System.out.println("Current batch id = " + currentBatchIdInt);
                    break;
                case "current quantity":
                    float currentQuantityFloat = (float) variant.getValue();
                    int currentQuantityInt = (int) currentQuantityFloat;
                    System.out.println("Current quantity being produced = " + currentQuantityInt);
                    break;
                case "products produced":
                    int productsProducedInt = (int) variant.getValue();
                    System.out.println("Products produced = " + productsProducedInt);
                    break;
                case "defective products produced":
                    int defectiveProductsProducedInt = (int) variant.getValue();
                    System.out.println("Defective products produced = " + defectiveProductsProducedInt);
                    break;
                case "stop reason id":
                    int stopReasonIdInt = (int)variant.getValue();
                    System.out.println("Stop reason id = " + stopReasonIdInt);
                    break;
                case "vibration":
                    float vibration = (float) variant.getValue();
                    System.out.println("Vibration = " + vibration);
                    break;
                case "relative humidity":
                    float relativeHumidity = (float) variant.getValue();
                    System.out.println("Relative humidity = " + relativeHumidity);
                    break;
                case "temperature":
                    float temperature = (float) variant.getValue();
                    System.out.println("Temperature = " + temperature);
                    break;
            }
    }


}
