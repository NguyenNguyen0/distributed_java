package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGenericService<T, ID> extends Remote {
    void save(T obj) throws RemoteException;
    void update(T obj) throws RemoteException;
    void delete(T obj) throws RemoteException;
    T findById(ID id) throws RemoteException;
    List<T> findAll() throws RemoteException;
}
