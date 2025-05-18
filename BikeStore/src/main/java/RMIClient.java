import dao.ICustomerDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        ICustomerDAO customerDAO = (ICustomerDAO) context.lookup("rmi://NGUYEN:2975/CustomerDAO");

        System.out.println(customerDAO.getNumberCustomerByState());
    }
}
