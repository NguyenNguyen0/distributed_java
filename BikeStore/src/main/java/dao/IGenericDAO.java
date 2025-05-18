package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGenericDAO<T, ID> extends Remote {
    void save(T object) throws RemoteException;
    void update(T object) throws RemoteException;
    void delete(T object) throws RemoteException;
    T findById(ID id) throws RemoteException;
    List<T> findAll() throws RemoteException;
}
