package Domain;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

public class Write {
    public static void setBatchQuantity() {
        writeValue(6, "::Program:Cube.Command.Parameter[2].Value", "batch quantity" ); }
    public static void setProductId() {
        writeValue(6, "::Program:Cube.Command.Parameter[1].Value", "product id" ); }
    public static void setBatchId() {
        writeValue(6, "::Program:Cube.Command.Parameter[0].Value", "current batch id"); }
    public static void setMachineSpeed() {
        writeValue(6, "::Program:Cube.Command.MachSpeed", "machine speed" ); }

    private static OpcUaClient client = null;

    public static void writeValue(int namespaceindex, String identifier, String parameterName) {
        client = OpcUAConnector.getOpcUaClient();


        NodeId nodeId = new NodeId(namespaceindex, identifier);

        try {
            client.writeValue(nodeId, DataValue.valueOnly(new Variant(System.in)) ).get();
        } catch (Exception e) {
            e.printStackTrace();
        }



        //the following is what is to be printed out, dependent on the parameterName given

    }
}
