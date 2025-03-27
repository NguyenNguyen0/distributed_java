package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class GenericDAO <T> {
    private final Class<T> type;
    protected final EntityManager em;

    public GenericDAO(Class<T> type) {
        this.type = type;
        this.em = Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();
    }

    public boolean add(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        try {
            em.getTransaction().begin();
            T entity = em.find(type, id);
            em.remove(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public T findById(Object id) {
        return em.find(type, id);
    }

    public List<T> findAll(int skip, int limit) {
        return (List<T>) em.createQuery("SELECT t FROM " + type.getSimpleName() + " t", type)
                .setFirstResult(skip)
                .setMaxResults(limit)
                .getResultList();
    }

}
