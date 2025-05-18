import dao.impl.GroupDAO;
import dao.impl.UserDAO;
import model.Group;
import model.User;

import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        GroupDAO groupDAO = new GroupDAO();

        // Create sample users
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setPassword("password123");
        user1.setEmail("john@example.com");
        user1.setGroups(new HashSet<>());

        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setPassword("password456");
        user2.setEmail("jane@example.com");
        user2.setGroups(new HashSet<>());

        User user3 = new User();
        user3.setUsername("mike_jones");
        user3.setPassword("password789");
        user3.setEmail("mike@example.com");
        user3.setGroups(new HashSet<>());

        // Create sample groups
        Group group1 = new Group();
        group1.setName("Developers");
        group1.setUsers(new HashSet<>());

        Group group2 = new Group();
        group2.setName("Testers");
        group2.setUsers(new HashSet<>());

        Group group3 = new Group();
        group3.setName("Managers");
        group3.setUsers(new HashSet<>());

        // Save users and groups to database
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);

        groupDAO.save(group1);
        groupDAO.save(group2);
        groupDAO.save(group3);

        // Add users to groups
        groupDAO.addUserToGroup(group1.getId(), user1.getId()); // John is a developer
        groupDAO.addUserToGroup(group1.getId(), user2.getId()); // Jane is a developer
        groupDAO.addUserToGroup(group2.getId(), user2.getId()); // Jane is a tester
        groupDAO.addUserToGroup(group2.getId(), user3.getId()); // Mike is a tester
        groupDAO.addUserToGroup(group3.getId(), user1.getId()); // John is a manager

        // Count users in each group
        System.out.println("User count per group:");
        Map<Group, Integer> groupUserCount = groupDAO.countGroupsUser();

        for (Map.Entry<Group, Integer> entry : groupUserCount.entrySet()) {
            Group group = entry.getKey();
            Integer userCount = entry.getValue();
            System.out.println(group.getName() + ": " + userCount + " users");
        }
    }
}