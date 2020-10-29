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

public class Read {
    public static void main(String[] args) {
        getCurrentQuantity();
        getCurrentBatchId();
        getCurrentProduct();
        getCurrentQuantity();
        getMachineSpeed();
    }

    //skal forestille at være en metode der henter en specifik variabels værdi, metoden skal overloades
    public static void getMachineSpeed() {
        readValue(6, "::Program:Cube.Status.CurMachSpeed", "machine speed" );
    }

    public static void getCurrentProduct() {
        readValue(6, "::Program:Cube.Admin.Parameter[0].Value", "current product" );
    }

    public static void getCurrentBatchId() {
        readValue(6, "::Program:Cube.Status.Parameter[0].Value", "current batch id");
    }

    public static void getCurrentQuantity() {
        readValue(6, "::Program:Cube.Status.Parameter[1].Value", "current quantity" );
    }


    public static void readValue(int namespaceindex, String identifier, String parameterName) {
        //At first we try to establish a connection to the software simulator url, if it connects we want it to print all endpoints
        try {
            org.apache.log4j.BasicConfigurator.configure(new NullAppender());
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();

            /*now we want to read specific nodes from the simulator
            firstly we create a OpcUaClientConfigBuilder object */
            OpcUaClientConfigBuilder configBuilder = new OpcUaClientConfigBuilder();
            // we set the endpoint of our object to the first endpoint from the list
            configBuilder.setEndpoint(endpoints.get(0));

            /*now we want to create a OpcUaClient object with the configuration from the previously created
            config object*/
            OpcUaClient client = OpcUaClient.create(configBuilder.build());
            client.connect().get();

            /* to read the value from a specific node, we can provide the namespace index as
            the first parameter, and the identifier string as the second parameter for the
            specific nodeId. Here we want to read the value corresponding to the cube.command.Machspeed */
            NodeId nodeId = new NodeId(namespaceindex, identifier);
            DataValue dataValue =client.readValue(0, TimestampsToReturn.Both, nodeId).get();

            /* now to simplify the response from the read operation, we want to store the read value that goes by
            "variant" as the variable specific to the operation. Here we start out by doing it for the machine speed
             */
            Variant variant = dataValue.getValue();

            //the following is what is to be printed out, dependent on the parameterName given
            if (parameterName == "machine speed") {
                float machineSpeed = (float) variant.getValue();
                System.out.println("machine speed = " + machineSpeed);
            }
            else if (parameterName == "current product"){
                float currentProductFloat = (float) variant.getValue();
                int currentProductInt = (int) currentProductFloat;
                System.out.println("current product = " + currentProductInt);
            }
            else if (parameterName == "current batch id"){
                float currentBatchIdFloat =(float) variant.getValue();
                int currentBatchIdInt = (int) currentBatchIdFloat;
                System.out.println("current batch id = " + currentBatchIdInt);
            }
            else if (parameterName == "current quantity") {
                float currentQuantityFloat = (float) variant.getValue();
                int currentQuantityInt = (int) currentQuantityFloat;
                System.out.println("current quantity being produced = " + currentQuantityInt);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
