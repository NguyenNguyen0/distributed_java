package server;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class DemoServerRMI {
    public static void main(String[] args) throws RemoteException, NamingException {
        LocateRegistry.createRegistry(1099);
        System.out.println("RMI Registry started");

        CalculatingRemoteImpl calculatingRemoteImpl = new CalculatingRemoteImpl();
        Context context = new InitialContext();
        context.bind("rmi://localhost/calculatingRemoteImpl", calculatingRemoteImpl);
        System.out.println("Server is running...");
    }
}

class CalculatingRemoteImpl extends UnicastRemoteObject implements CalculatingRemote {
    public CalculatingRemoteImpl() throws RemoteException {}

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public int divide(int a, int b) throws RemoteException {
        return a / b;
    }
}