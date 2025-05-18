package edu.iuh.fit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GenericService<T, ID> extends Remote {

    T findById(ID id) throws RemoteException;
    boolean save(T t) throws RemoteException;
    List<T> getAll() throws RemoteException;

}
