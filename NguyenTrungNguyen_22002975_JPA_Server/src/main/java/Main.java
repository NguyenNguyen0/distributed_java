import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Student;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        var em = Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();
        var tr = em.getTransaction();
        tr.begin();
        var student = new Student("Lmao", "lmao", LocalDateTime.of(2025, 12, 12, 0, 0, 0));
        em.persist(student);
        tr.commit();
    }
}
