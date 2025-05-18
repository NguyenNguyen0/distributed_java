package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public abstract class GenericDAO<T, ID> extends UnicastRemoteObject implements IGenericDAO  {
    protected EntityManager em;
    protected Class<T> clazz;

    protected GenericDAO(Class<T> clazz) {
        this.em = JPAUtil.getEntityManager();
        this.clazz = clazz;
    }

    public void save(T object) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(object);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    public void update(T object) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(object);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    public void delete(T object) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.remove(object);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    public T findById(ID id) {
        return em.find(clazz, id);
    }

    public List<T> findAll() {
        return em.createQuery("select t from " + clazz.getSimpleName() + " t", clazz).getResultList();
    }
}
