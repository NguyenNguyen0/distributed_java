package dao.impl;

import dao.IUserDAO;
import model.Group;
import model.User;

public class UserDAO extends GenericDAO<User, Integer> implements IUserDAO {
    public UserDAO() {
        super(User.class);
    }

    @Override
    public User getByUsername(String username) {
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        var result = em.createQuery(jpql, User.class)
                .setParameter("username", username);
        if (result.getResultList().isEmpty()) {
            return null;
        } else {
            return result.getSingleResult();
        }
    }

    @Override
    public User getByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        var result = em.createQuery(jpql, User.class)
                .setParameter("email", email);
        if (result.getResultList().isEmpty()) {
            return null;
        } else {
            return result.getSingleResult();
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        return !em.createQuery(jpql, User.class)
                .setParameter("username", username).getResultList().isEmpty();
    }

    @Override
    public boolean isEmailExists(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        return !em.createQuery(jpql, User.class)
                .setParameter("email", email).getResultList().isEmpty();
    }

    @Override
    public void addGroupToUser(int userId, int groupId) {
        User user = getById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        GroupDAO groupDao = new GroupDAO();
        Group group = groupDao.getById(groupId);
        if (group == null) {
            throw new RuntimeException("Group not found");
        }

        user.getGroups().add(group);
        group.getUsers().add(user);
        update(user);
        groupDao.update(group);
    }

    @Override
    public void removeGroupFromUser(int userId, int groupId) {
        User user = getById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        GroupDAO groupDao = new GroupDAO();
        Group group = groupDao.getById(groupId);
        if (group == null) {
            throw new RuntimeException("Group not found");
        }

        user.getGroups().remove(group);
        group.getUsers().remove(user);
        update(user);
        groupDao.update(group);
    }
}
