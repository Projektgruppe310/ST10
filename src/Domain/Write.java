package Domain;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

import java.util.Scanner;

public class Write {

    public static void setBatchQuantity(int quantity) {
        write(6, "::Program:Cube.Command.Parameter[2].Value", "batch quantity", quantity);
    }
    public static void setProductId(int productId) {

        write(6, "::Program:Cube.Command.Parameter[1].Value", "product id", productId);
    }
    public static void setBatchId(int batchId) {

        write(6, "::Program:Cube.Command.Parameter[0].Value", "current batch id", batchId);
    }
    public static void setMachineSpeed(int machineSpeed) {

        write(6, "::Program:Cube.Command.MachSpeed", "machine speed", machineSpeed);
    }

    private static OpcUaClient client = null;

    public static void write(int namespaceindex, String identifier, String parameterName, int inputValue) {
        client = OpcUAConnector.getOpcUaClient();
        NodeId nodeId = new NodeId(namespaceindex, identifier);
        try {
            client.writeValue(nodeId, DataValue.valueOnly(new Variant(inputValue)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createBatch() {
            Scanner scanner = new Scanner(System.in);
            //int batchId, productId, batchQuantity, machineSpeed;

            System.out.println("Assign an ID to the batch");
            int batchId = Integer.parseInt(scanner.nextLine());
            setBatchId(batchId);

            System.out.println("Provide desired product ID");
            int productId = Integer.parseInt(scanner.nextLine());
            setProductId(productId);

            System.out.println("Provide desired batch quantity");
            int batchQuantity = Integer.parseInt(scanner.nextLine());
            setBatchQuantity(batchQuantity);

            System.out.println("Provide desired machine speed (products per minute)");
            int machineSpeed = Integer.parseInt(scanner.nextLine());
            setMachineSpeed(machineSpeed);
    }
}
