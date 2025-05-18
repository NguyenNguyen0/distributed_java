package dao;

import jakarta.persistence.EntityManager;
import utils.JPAUtil;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public abstract class GenericDAO<T, ID> extends UnicastRemoteObject {
    protected EntityManager em;
    protected Class<T> aClass;

    public GenericDAO(Class<T> aClass) {
        this.em = JPAUtil.getEntityManager();
        this.aClass = aClass;
    }

    public boolean add(T o) {
        var tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(o);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(T o) {
        var tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(o);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(ID id) {
        var tr = em.getTransaction();
        try {
            tr.begin();
            T o = em.find(aClass, id);
            if (o != null) {
                em.remove(id);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public T findById(ID id) {
        return em.find(aClass, id);
    }

    public List<T> getAll() {
        return em.createQuery("SELECT t FROM " + aClass.getSimpleName() + " t").getResultList();
    }
}
