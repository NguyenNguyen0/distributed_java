package dao.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGenericDAO<T, ID> implements Remote {
    boolean add(T o) throws RemoteException;

}
