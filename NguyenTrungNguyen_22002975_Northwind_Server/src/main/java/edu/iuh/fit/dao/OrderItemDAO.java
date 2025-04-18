package edu.iuh.fit.dao;

import edu.iuh.fit.model.Order;
import edu.iuh.fit.model.OrderItem;
import edu.iuh.fit.model.Product;
import jakarta.persistence.EntityManager;

public class OrderItemDAO extends GenericDAO<OrderItem,OrderItem.OrderItemId> {
    public OrderItemDAO(Class<OrderItem> clazz) {
        super(clazz);
    }

    public OrderItemDAO(EntityManager em, Class<OrderItem> clazz) {
        super(em, clazz);
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO(Product.class);
        OrderDAO orderDAO = new OrderDAO(Order.class);
        OrderItemDAO orderItemDAO = new OrderItemDAO(OrderItem.class);

        Product product = productDAO.findById(4);
        Order order = orderDAO.findById(1);
        OrderItem orderItem = orderItemDAO.findById(new OrderItem.OrderItemId(order, product));

        System.out.println(orderItem);
    }
}
