package data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Group;
import model.User;
import net.datafaker.Faker;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

        EntityTransaction tr = em.getTransaction();

        Faker faker = new Faker();
        Random rd = new Random();

        // Create 3 groups

        String [] groups = {"Admin", "User", "Manager"};
        for (int i = 0; i < groups.length; i++) {
            Group group = new Group();
            group.setName(groups[i]);

            tr.begin();
                em.persist(group);
            tr.commit();
        }

//        Create 10 users with random group
        for (int i = 0; i < 10; i++) {
            User user = new User();

            String us = faker.internet().username();
            String pw = faker.internet().password();
            String email = us+"@gmail.com";

            user.setUsername(us);
            user.setPassword(pw);
            user.setEmail(email);

            int gID = rd.nextInt(3) + 1;
            int gID2 = rd.nextInt(2)+ 1;
            int gID3 = rd.nextInt(2)+ 2;

            Group group = em.find(Group.class, gID);
            Group group2 = em.find(Group.class, gID2);
            Group group3 = em.find(Group.class, gID3);

            user.setGroups(new HashSet<>(List.of(group, group2, group3)));

            tr.begin();
            em.persist(user);
            tr.commit();
        }


    }
}
