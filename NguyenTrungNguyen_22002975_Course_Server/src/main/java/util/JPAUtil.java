package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("mariadb-pu");
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

}
