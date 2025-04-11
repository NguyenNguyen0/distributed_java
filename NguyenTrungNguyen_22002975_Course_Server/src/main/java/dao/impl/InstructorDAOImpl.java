package dao.impl;

import dao.GenericDAO;
import dao.InstructorDAO;
import jakarta.persistence.EntityManager;
import model.Instructor;

public class InstructorDAOImpl extends GenericDAOImpl<Instructor, Integer> implements InstructorDAO {
    public InstructorDAOImpl(Class<Instructor> clazz) {
        super(clazz);
    }

    public InstructorDAOImpl(EntityManager em, Class<Instructor> clazz) {
        super(em, clazz);
    }
}
