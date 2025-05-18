import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(2975);

        context.bind("rmi://NGUYEN:2975/UserService", new service.impl.UserService(new dao.impl.UserDAO()));
        context.bind("rmi://NGUYEN:2975/GroupService", new service.impl.GroupService(new dao.impl.GroupDAO()));

        System.out.printf("Server is running in port 2975...");
    }
}
