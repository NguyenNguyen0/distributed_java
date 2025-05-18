package service.impl;

import dao.impl.GenericDAO;
import dao.impl.GroupDAO;
import model.Group;
import service.IGroupService;

import java.rmi.RemoteException;
import java.util.Map;

public class GroupService extends GenericService<Group, Integer> implements IGroupService {
    private GroupDAO groupDAO;
    public GroupService(GroupDAO groupDAO) throws RemoteException {
        super(groupDAO);
        this.groupDAO = groupDAO;
    }

    @Override
    public Group getByName(String name) throws RemoteException {
        return groupDAO.getByName(name);
    }

    @Override
    public boolean isNameExists(String name) throws RemoteException {
        return groupDAO.isNameExists(name);
    }

    @Override
    public void addUserToGroup(int groupId, int userId) throws RemoteException {
        groupDAO.addUserToGroup(groupId, userId);
    }

    @Override
    public void removeUserFromGroup(int groupId, int userId) throws RemoteException {
        groupDAO.removeUserFromGroup(groupId, userId);
    }

    @Override
    public Map<Group, Integer> countGroupsUser() throws RemoteException {
        return groupDAO.countGroupsUser();
    }
}
