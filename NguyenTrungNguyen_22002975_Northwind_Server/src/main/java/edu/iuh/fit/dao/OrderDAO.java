package edu.iuh.fit.dao;

import edu.iuh.fit.model.Order;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class OrderDAO extends GenericDAO<Order, Integer> {
    public OrderDAO(Class<Order> clazz) {
        super(clazz);
    }

    public OrderDAO(EntityManager em, Class<Order> clazz) {
        super(em, clazz);
    }

//  Calculate the total amount of all bills for a certain day.
    public double getTotalAmount(LocalDate orderDate){
        String jpql = "select sum(oi.quantity * (1 - oi.discount) * oi.listPrice) " +
                "from OrderItem oi " +
                "where oi.order.orderDate = :orderDate ";

        return em.createQuery(jpql, Double.class)
                .setParameter("orderDate", orderDate)
                .getSingleResult();
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO(Order.class);
        double total = orderDAO.getTotalAmount(LocalDate.of(2016,1,1));
        System.out.println(total);
    }

}
