import jakarta.json.JsonArray;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.User;
import util.UserUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ToJson {
    public static void main(String[] args) throws IOException {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> users = query.getResultList();

        users.forEach(System.out::println);

        UserUtil userUtil = new UserUtil();
        JsonArray usersJson = userUtil.userToJson(users);
        System.out.println(usersJson);

        File file = new File("json/users.json");
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        userUtil.writeJsonArrayToFile(usersJson, file);
    }
}
