package client;

import server.CalculatingRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class DemoClientRMI {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        CalculatingRemote calculatingRemote = (CalculatingRemote) context.lookup("rmi://localhost/calculatingRemoteImpl");
        System.out.println("Addition: " + calculatingRemote.add(10, 5));
        System.out.println("Subtraction: " + calculatingRemote.subtract(10, 5));
        System.out.println("Multiplication: " + calculatingRemote.multiply(10, 5));
        System.out.println("Division: " + calculatingRemote.divide(10, 5));
    }
}
