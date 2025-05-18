package dao;

import models.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Map;

public interface IOrderDAO extends Remote {
    double calculateOrderTotalAmount(int id) throws RemoteException;
    Map<Customer, Long> getNumberOfOrdersForEachCustomer() throws RemoteException;
    double calculateAllOrderTotalAmountInCertainDay(LocalDate orderDate) throws RemoteException;
    double calculateAllOrderTotalAmountByMonthYear(int month, int year) throws RemoteException;
}
