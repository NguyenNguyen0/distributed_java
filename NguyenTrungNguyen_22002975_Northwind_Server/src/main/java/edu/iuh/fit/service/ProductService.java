package edu.iuh.fit.service;

import edu.iuh.fit.model.Product;

import java.rmi.RemoteException;
import java.util.List;

public interface ProductService extends GenericService<Product,Integer> {

    List<Product> listProductsNotBeenSold() throws RemoteException;
    List<Product> listProductsWithTheHighestPrice() throws RemoteException;

}
