package edu.iuh.fit.service.impl;

import edu.iuh.fit.dao.CustomerDAO;
import edu.iuh.fit.model.Customer;
import edu.iuh.fit.service.CustomerService;

import java.rmi.RemoteException;

public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer> implements CustomerService {
    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) throws RemoteException {
        super(customerDAO);
        this.customerDAO = customerDAO;
    }
}
