package edu.iuh.fit.dao;

import edu.iuh.fit.model.Customer;
import jakarta.persistence.EntityManager;

public class CustomerDAO extends GenericDAO<Customer, Integer> {
    public CustomerDAO(Class<Customer> clazz) {
        super(clazz);
    }

    public CustomerDAO(EntityManager em, Class<Customer> clazz) {
        super(em, clazz);
    }


    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO(Customer.class);
        Customer customer = customerDAO.findById(1);
        System.out.println(customer);
    }
}
