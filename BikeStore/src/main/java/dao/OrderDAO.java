package dao;

import models.Customer;
import models.Order;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderDAO extends GenericDAO<Order, Integer> implements IOrderDAO {
    public OrderDAO() throws RemoteException {
        super(Order.class);
    }

    @Override
    public double calculateOrderTotalAmount(int id) throws RemoteException {
        String jpql = "SELECT SUM(oi.listPrice * oi.quantity) FROM OrderItem oi WHERE oi.order.id = :id";

        Double result = em.createQuery(jpql, Double.class)
                .setParameter("id", id)
                .getSingleResult();

        return result != null ? result : 0.0;
    }

    @Override
    public Map<Customer, Long> getNumberOfOrdersForEachCustomer() throws RemoteException {
        String jpql = "SELECT c, COUNT(o) FROM Customer c LEFT JOIN c.orders o GROUP BY c";

        List<Object[]> result = em.createQuery(jpql).getResultList();

        Map<Customer, Long> map = new LinkedHashMap<>();

        for (Object[] o : result) {
            Customer customer = (Customer) o[0];
            Long count = (Long) o[1];
            map.put(customer, count);
        }

        return map;
    }

    @Override
    public double calculateAllOrderTotalAmountInCertainDay(LocalDate orderDate) throws RemoteException {
        String jpql = "SELECT SUM(oi.listPrice * oi.quantity) FROM Order o LEFT JOIN o.orderItems oi WHERE o.orderDate = :orderDate";

        Double result = em.createQuery(jpql, Double.class)
                .setParameter("orderDate", orderDate)
                .getSingleResult();

        return result != null ? result : 0.0;
    }

    @Override
    public double calculateAllOrderTotalAmountByMonthYear(int month, int year) throws RemoteException {
        String jpql = "SELECT SUM(oi.listPrice * oi.quantity) " +
                "FROM Order o LEFT JOIN o.orderItems oi " +
                "WHERE year(o.orderDate) = :year and month(o.orderDate) = :month";

        Double result = em.createQuery(jpql, Double.class)
                .setParameter("month", month)
                .setParameter("year", year)
                .getSingleResult();

        return result != null ? result : 0.0;
    }
}
