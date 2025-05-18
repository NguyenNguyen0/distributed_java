import Util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Address;
import model.Course;
import net.datafaker.Faker;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager("mariadb");
//        em.getTransaction().begin();

//        Faker faker = new Faker();
//        for (int i = 0; i < 10; i++) {
//            Course course = new Course();
//            course.setId(faker.number().digits(5));
//            course.setName(faker.educator().course());
//            course.setCredits(faker.number().numberBetween(1, 5));
////            em.persist(course);
//        }

//        em.getTransaction().commit();
//        em.close();
    }
}
