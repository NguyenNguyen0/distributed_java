package dao;

import models.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface ICustomerDAO extends Remote {
    Map<String, Integer> getNumberCustomerByState() throws RemoteException;
}
