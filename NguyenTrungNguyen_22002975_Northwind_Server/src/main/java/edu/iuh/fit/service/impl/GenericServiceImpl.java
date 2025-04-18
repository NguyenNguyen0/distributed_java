package edu.iuh.fit.service.impl;

import edu.iuh.fit.dao.GenericDAO;
import edu.iuh.fit.service.GenericService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public abstract class GenericServiceImpl<T, ID> extends UnicastRemoteObject implements GenericService<T, ID> {

    protected GenericDAO<T, ID> genericDAO;

    public GenericServiceImpl(GenericDAO<T, ID> genericDAO) throws RemoteException{
        this.genericDAO = genericDAO;
    }

    @Override
    public T findById(ID id) throws RemoteException {

        return genericDAO.findById(id);
    }

    @Override
    public boolean save(T t) throws RemoteException {
        return genericDAO.save(t);
    }

    @Override
    public List<T> getAll() throws RemoteException {
        return genericDAO.getAll();
    }
}
