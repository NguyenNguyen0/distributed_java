package dao;

import Util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Course;

import java.util.List;

public class CourseDAO {
    public static List<Course> getAll() {
        EntityManager em = JPAUtil.getEntityManager("default");
        List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
        em.close();
        return courses;
    }
}
