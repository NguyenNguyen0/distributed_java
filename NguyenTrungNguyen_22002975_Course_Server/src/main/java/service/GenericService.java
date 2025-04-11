package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GenericService<T, ID>  extends Remote {

    boolean save(T t) throws RemoteException;
    boolean update(T t) throws RemoteException;
    T findById(ID id) throws RemoteException;
    List<T> getAll() throws RemoteException;

}
