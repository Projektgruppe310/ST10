package Domain;

import org.apache.log4j.varia.NullAppender;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;

import java.util.List;

public class OpcUAConnector {

    public static OpcUaClient client = null;

    public static OpcUaClient getOpcUaClient() {


        try {
            org.apache.log4j.BasicConfigurator.configure(new NullAppender());
            
            // Simulation
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();
                EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(0), "127.0.0.1", 4840);
            
            // Beer Machine
            //List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://192.168.0.122:4840").get();
            //EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(0), "192.168.0.122", 4840);

            /*now we want to read specific nodes from the simulator
            firstly we create a OpcUaClientConfigBuilder object */
            OpcUaClientConfigBuilder configBuilder = new OpcUaClientConfigBuilder();
            // we set the endpoint of our object to the first endpoint from the list
            configBuilder.setEndpoint(configPoint);

            /*now we want to create a OpcUaClient object with the configuration from the previously created
            config object*/
            client = OpcUaClient.create(configBuilder.build());
            client.connect().get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return client;

    }
}
