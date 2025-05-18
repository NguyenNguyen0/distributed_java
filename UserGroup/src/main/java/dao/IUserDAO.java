package dao;

import model.User;

public interface IUserDAO extends IGenericDAO<User, Integer> {
    User getByUsername(String username);
    User getByEmail(String email);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
    void addGroupToUser(int userId, int groupId);
    void removeGroupFromUser(int userId, int groupId);
}
