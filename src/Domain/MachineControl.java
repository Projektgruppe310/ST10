package Domain;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public class MachineControl {

    // Static connection to OpcUAConnector client
    private static OpcUaClient client = null;

    // Uses the OpcUaClient to write to the CntrlCmd command in UaExpert. Namespace 6.
    // Uses cmd as argument and sends the client and cmd to machineChangeRequest, which confirms the command.
    protected void machineCntrlCmd(int cmd){

        client = OpcUAConnector.getOpcUaClient();

        try{
            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
            client.writeValue(nodeId, DataValue.valueOnly(new Variant(cmd))).get();

            System.out.println(machineChangeRequest(client, cmd));

        } catch (Throwable ex){
            ex.printStackTrace();
            System.out.println("Something went wrong with the command");
        }
    }

    // Uses the client to make CmdChangeRequest true. The cmd is used in a switch to return the correct feedback.
    // Feedback needs work / understanding.
    private String machineChangeRequest(OpcUaClient client, int cmd){
        try{

            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
            client.writeValue(nodeId,DataValue.valueOnly(new Variant(true))).get();

            switch(cmd){
                case 0: {
                    //Nothing
                }
                case 1: { // 1 - Reset
                    return "Machine resetted!";

                }
                case 2: { // 2 - Start
                    return "Machine started!";
                }
                case 3: { // 3 - Stop
                    return "Machine stopped!";
                }
                case 4: { // 4 - Abort
                    return "Machine aborted!"; // uuuuuuuum

                }
                case 5: { // 5 - Clear
                    return "Machine cleared!"; // Oooook?
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
