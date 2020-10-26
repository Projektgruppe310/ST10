import org.apache.log4j.varia.NullAppender;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import java.util.List;




    public class ConnectOPC{

            public static void main(String[] args) {
            try {
                org.apache.log4j.BasicConfigurator.configure(new NullAppender());
                List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://localhost:12686/milo").get();
                System.out.println("Endpoints - " + endpoints);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }
