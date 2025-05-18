package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGenericDAO<T, ID> {
    public void save(T t);
    public void update(T t);
    public void delete(ID id);
    T getById(ID id);
    List<T> getAll();
}
