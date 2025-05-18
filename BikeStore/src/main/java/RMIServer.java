import dao.CustomerDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        Registry loc = LocateRegistry.createRegistry(2975);

        context.bind("rmi://NGUYEN:2975/CustomerDAO", new CustomerDAO());
        System.out.println("Server rmi is running...");
    }
}
