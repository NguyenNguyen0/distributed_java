package dao;

import model.Group;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IGroupDAO extends IGenericDAO<Group, Integer> {
    Group getByName(String name);
    boolean isNameExists(String name);
    void addUserToGroup(int groupId, int userId);
    void removeUserFromGroup(int groupId, int userId);
    Map<Group, Integer> countGroupsUser();
}
