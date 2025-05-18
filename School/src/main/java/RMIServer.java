import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(2975);

        context.bind("rmi://NGUYEN:2975/studentService", new service.impl.StudentService(new dao.StudentDAO()));
        context.bind("rmi://NGUYEN:2975/departmentService", new service.impl.DepartmentService(new dao.DepartmentDAO()));

        System.out.println( "RMI Server started...");
    }
}
