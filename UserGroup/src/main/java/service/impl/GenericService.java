package service.impl;

import dao.impl.GenericDAO;
import service.IGenericService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GenericService<T, ID> extends UnicastRemoteObject implements IGenericService<T, ID> {
    protected final GenericDAO<T, ID> dao;

    public GenericService(GenericDAO<T, ID> genericDAO) throws RemoteException {
        this.dao = genericDAO;
    }

    @Override
    public void save(T o) throws RemoteException {
        dao.save((T) o);
    }

    @Override
    public void update(T o) throws RemoteException {
        dao.update((T) o);
    }

    @Override
    public void delete(ID o) throws RemoteException {
        dao.delete((ID) o);
    }

    @Override
    public T getById(ID o) throws RemoteException {
        return dao.getById((ID) o);
    }

    @Override
    public List<T> getAll() throws RemoteException {
        return dao.getAll();
    }
}
