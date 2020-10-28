package Domain;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MachineControl {



    public String machineSwitch(int cmd){

        try {
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();

            OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();
            cfg.setEndpoint(endpoints.get(0));

            OpcUaClient client = OpcUaClient.create(cfg.build());
            client.connect().get();

            machineCntrlCmd(client, cmd);

        } catch (Exception ex) {
                ex.printStackTrace();
            }

            return "Connection failed!";
    }

    private String machineCntrlCmd(OpcUaClient client, int cmd){
        try{
            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
            client.writeValue(nodeId, DataValue.valueOnly(new Variant(cmd))).get();

            machineChangeRequest(client, cmd);



        } catch (Throwable ex){
            ex.printStackTrace();
        }
        return "Something went wrong with the command";
    }

    private String machineChangeRequest(OpcUaClient client, int cmd){
        try{

            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
            client.writeValue(nodeId,DataValue.valueOnly(new Variant(true))).get();

            switch(cmd){
                case 0: {
                    //Nothing
                }
                case 1: { // 1 - Reset

                }
                case 2: { // 2 - Start
                    return "Machine started!";
                }
                case 3: { // 3 - Stop
                    return "Machine stopped!";
                }
                case 4: { // 4 - Abort

                }
                case 5: { // 5 - Clear

                }
                default:
                    return "Invalid command.";
            }


        } catch (Throwable ex){
            ex.printStackTrace();
        }
        return "Something went wrong with the command request.";
    }

}
