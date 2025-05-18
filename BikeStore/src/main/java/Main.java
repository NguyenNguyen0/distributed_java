import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import org.checkerframework.checker.units.qual.C;
import utils.JPAUtil;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws RemoteException {
//        ProductDAO productDAO = new ProductDAO();
//        System.out.println(productDAO.getProductWithHighestPrice());
//        System.out.println(productDAO.getProductHaveNotSoldYet());
//        System.out.println(productDAO.getTotalQuantityOfEachProductSold());

//        CustomerDAO customerDAO = new CustomerDAO();
//        System.out.println(customerDAO.getNumberCustomerByState());

        OrderDAO orderDAO = new OrderDAO();
//        System.out.println(orderDAO.calculateAllOrderTotalAmountInCertainDay(LocalDate.of(2016, 1, 1)));
        System.out.println(orderDAO.calculateAllOrderTotalAmountByMonthYear(2, 2016));
//        System.out.println(orderDAO.calculateOrderTotalAmount(1));
//        System.out.println(orderDAO.getNumberOfOrdersForEachCustomer());


    }
}
