package dao;

import models.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IProductDAO extends Remote {
    List<Product> getProductWithHighestPrice() throws RemoteException;
    List<Product> getProductHaveNotSoldYet() throws RemoteException;
    Map<Product, Long> getTotalQuantityOfEachProductSold() throws RemoteException;

}
