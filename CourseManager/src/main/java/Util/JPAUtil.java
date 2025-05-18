package Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;

public class JPAUtil {
    private static final HashMap<String, EntityManager> emMap = new HashMap<>();

    public static EntityManager getEntityManager(String persistenceUnitName) {
        if (emMap.containsKey(persistenceUnitName)) {
            return emMap.get(persistenceUnitName);
        }

        EntityManager em = JPAUtil.createEntityManager(persistenceUnitName);
        emMap.put(persistenceUnitName, em);

        return em;
    }

    private static EntityManager createEntityManager(String persistenceUnitName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();
        emMap.put(persistenceUnitName, em);
        return em;
    }
}
