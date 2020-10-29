package main;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.List;

//import org.apache.log4j.varia.NullAppender;
public class Read {

        public static void main(String[] args) {
            //At first we try to establish a connection to the software simulator url, if it connects we want it to print all endpoints
            try {
               // org.apache.log4j.BasicConfigurator.configure(new NullAppender());
                List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://localhost:4840").get();
                //System.out.println("Endpoints - " + endpoints);


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
                NodeId nodeId = new NodeId(6, "::Program:Cube.Admin.ProdProcessedCount");
                DataValue dataValue =client.readValue(0, TimestampsToReturn.Both, nodeId).get();
                NodeId nodeId2 = new NodeId(6, "::Program:Cube.Admin.ProdProcessedCount");
                DataValue dataValue2 =client.readValue(0, TimestampsToReturn.Both, nodeId).get();

            /* now to simplify the response from the read operation, we want to store the read value that goes by
            "variant" as the variable specific to the operation. Here we start out by doing it for the machine speed
             */
                Variant variant =dataValue.getValue();
                Collect collect = new Collect();

                int machineSpeed = (int)variant.getValue();
                System.out.println("machineSpeed = " + machineSpeed);
                collect.setTemperature(machineSpeed);
                collect.getCollec();





            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

