package edu.iuh.fit.rmi;

import edu.iuh.fit.dao.CustomerDAO;
import edu.iuh.fit.dao.ProductDAO;
import edu.iuh.fit.model.Customer;
import edu.iuh.fit.model.Product;
import edu.iuh.fit.service.CustomerService;
import edu.iuh.fit.service.ProductService;
import edu.iuh.fit.service.impl.CustomerServiceImpl;
import edu.iuh.fit.service.impl.ProductServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) throws Exception{

        Context context = new InitialContext();
        LocateRegistry.createRegistry(9090);

        CustomerDAO customerDAO = new CustomerDAO(Customer.class); //Java Object
        ProductDAO productDAO = new ProductDAO(Product.class); //Java Object

        CustomerService customerService = new CustomerServiceImpl(customerDAO); //Java Remote Object
        ProductService productService = new ProductServiceImpl(productDAO); //Java Remote Object

        context.bind("rmi://H83M17:9090/customerService", customerService);
        context.bind("rmi://H83M17:9090/productService", productService);

        System.out.println("RMI Server is running...");
    }
}
