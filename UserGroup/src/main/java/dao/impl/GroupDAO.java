package dao.impl;

import dao.IGroupDAO;
import model.Group;
import model.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupDAO extends GenericDAO<Group, Integer> implements IGroupDAO {
    public GroupDAO() {
        super(Group.class);
    }

    @Override
    public Group getByName(String name) {
        String jpql = "SELECT g FROM Group g WHERE g.name = :name";
        var result = em.createQuery(jpql, Group.class)
                .setParameter("name", name);
        if (result.getResultList().isEmpty()) {
            return null;
        } else {
            return result.getSingleResult();
        }
    }

    @Override
    public boolean isNameExists(String name) {
        String jpql = "SELECT g FROM Group g WHERE g.name = :name";
        return !em.createQuery(jpql, Group.class).setParameter("name", name).getResultList().isEmpty();
    }

    @Override
    public void addUserToGroup(int groupId, int userId) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Group group = getById(groupId);
        if (group == null) {
            throw new RuntimeException("Group not found");
        }

        user.getGroups().add(group);
        group.getUsers().add(user);
        userDAO.update(user);
        update(group);
    }

    @Override
    public void removeUserFromGroup(int groupId, int userId) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Group group = getById(groupId);
        if (group == null) {
            throw new RuntimeException("Group not found");
        }

        user.getGroups().remove(group);
        group.getUsers().remove(user);
        userDAO.update(user);
        update(group);
    }

    @Override
    public Map<Group, Integer> countGroupsUser() {
        String jpql = "SELECT g, COUNT(u) FROM Group g LEFT JOIN g.users u GROUP BY g ORDER BY COUNT(u) DESC";
        List<Object[]> results = em.createQuery(jpql, Object[].class).getResultList();
        Map<Group, Integer> map = new LinkedHashMap<>();
        for (Object[] row : results) {
            Group group = (Group) row[0];
            Long count = (Long) row[1];
            map.put(group, count.intValue());
        }
        return map;
    }
}
