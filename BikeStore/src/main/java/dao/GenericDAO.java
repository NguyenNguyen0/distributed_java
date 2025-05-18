package dao;

import jakarta.persistence.EntityManager;
import utils.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GenericDAO<T, ID> extends UnicastRemoteObject implements IGenericDAO<T, ID> {
    protected EntityManager em;
    protected Class<T> aClass;

    public GenericDAO(Class<T> aClass) throws RemoteException {
        this.em = JPAUtil.getEntityManager();
        this.aClass = aClass;
    }

    @Override
    public void save(T object) throws RemoteException {
        var tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(object);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(T object) throws RemoteException {
        var tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(object);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T object) throws RemoteException {
        var tr = em.getTransaction();
        try {
            tr.begin();
            em.remove(object);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public T findById(ID id) throws RemoteException {
        return em.find(aClass, id);
    }

    @Override
    public List<T> findAll() throws RemoteException {
        return em.createQuery("SELECT o FROM " + aClass.getSimpleName() + " o", aClass).getResultList();
    }
}
