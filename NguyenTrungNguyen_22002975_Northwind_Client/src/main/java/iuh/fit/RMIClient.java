package iuh.fit;

import edu.iuh.fit.model.Customer;
import edu.iuh.fit.service.CustomerService;
import edu.iuh.fit.service.ProductService;

import java.rmi.Naming;

public class RMIClient {

    public static void main(String[] args) throws Exception {
        ProductService productService = (ProductService) Naming.lookup("rmi://H83M17:9090/productService");
        CustomerService customerService = (CustomerService) Naming.lookup("rmi://H83M17:9090/customerService");

//        productService.getAll().forEach(p -> System.out.println(p));
//        productService.listProductsWithTheHighestPrice()
//                .forEach(p -> System.out.println(p));

        Customer customer = customerService.findById(1);
        System.out.println(customer);
    }
}
