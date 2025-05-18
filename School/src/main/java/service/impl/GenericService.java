package service.impl;

import dao.GenericDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public abstract class GenericService<T, ID> extends UnicastRemoteObject implements service.IGenericService<T, ID> {
    protected GenericDAO<T, ID> genericDAO;

    protected GenericService(GenericDAO<T, ID> genericDAO) throws RemoteException {
        this.genericDAO = genericDAO;
    }

    @Override
    public void save(T obj) throws RemoteException {
        genericDAO.save(obj);
    }

    @Override
    public void update(T obj) throws RemoteException {
        genericDAO.update(obj);
    }

    @Override
    public void delete(T obj) throws RemoteException {
        genericDAO.delete(obj);
    }

    @Override
    public T findById(ID id) throws RemoteException {
        return genericDAO.findById(id);
    }

    @Override
    public List<T> findAll() throws RemoteException {
        return genericDAO.findAll();
    }
}
