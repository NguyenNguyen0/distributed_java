package service;

import model.Group;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IGroupService extends IGenericService<Group, Integer> {
    Group getByName(String name) throws RemoteException;
    boolean isNameExists(String name) throws RemoteException;
    void addUserToGroup(int groupId, int userId) throws RemoteException;
    void removeUserFromGroup(int groupId, int userId) throws RemoteException;
    Map<Group, Integer> countGroupsUser() throws RemoteException;
}
