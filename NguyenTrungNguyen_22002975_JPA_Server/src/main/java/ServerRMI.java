import rmi.service.DepartmentService;
import rmi.service.StudentService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerRMI {
    public static void main(String[] args) {
        try {
            System.out.println("Server started...");
            LocateRegistry.createRegistry(2975);
            DepartmentService departmentService = new DepartmentService();
            StudentService studentService = new StudentService();
            Context context = new InitialContext();
            context.bind("rmi://localhost:2975/departmentService", departmentService);
            context.bind("rmi://localhost:2975/studentService", studentService);
            System.out.println("Student and Department bound in registry");
        } catch (RemoteException | NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
