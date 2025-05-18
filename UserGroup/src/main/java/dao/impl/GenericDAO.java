package dao.impl;


import dao.IGenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class GenericDAO<T, ID> implements IGenericDAO<T, ID> {
    protected EntityManager em = util.JPAUtil.getEntityManager();
    protected Class<T> clazz;

    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(t);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(t);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ID id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.remove(em.find(clazz, id));
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public T getById(ID id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> getAll() {
        return em.createQuery("from " + clazz.getSimpleName() + " t", clazz)
                .getResultList();
    }

}
