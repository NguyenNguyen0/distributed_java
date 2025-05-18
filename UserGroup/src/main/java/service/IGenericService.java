package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGenericService <T, ID> extends Remote {
    public void save(T t) throws RemoteException;
    public void update(T t) throws RemoteException;
    public void delete(ID id) throws RemoteException;
    T getById(ID id) throws RemoteException;
    List<T> getAll() throws RemoteException;
}
