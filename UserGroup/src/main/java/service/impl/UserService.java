package service.impl;

import dao.impl.UserDAO;
import model.User;

import java.rmi.RemoteException;

public class UserService extends GenericService<model.User, Integer> implements service.IUserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) throws RemoteException {
        super(userDAO);
        this.userDAO = userDAO;
    }

    @Override
    public User getByUsername(String username) throws RemoteException {
        return userDAO.getByUsername(username);
    }

    @Override
    public User getByEmail(String email) throws RemoteException {
        return userDAO.getByEmail(email);
    }

    @Override
    public boolean isUsernameExists(String username) throws RemoteException {
        return userDAO.isUsernameExists(username);
    }

    @Override
    public boolean isEmailExists(String email) throws RemoteException {
        return userDAO.isEmailExists(email);
    }

    @Override
    public void addGroupToUser(int userId, int groupId) throws RemoteException {
        userDAO.addGroupToUser(userId, groupId);
    }

    @Override
    public void removeGroupFromUser(int userId, int groupId) throws RemoteException {
        userDAO.removeGroupFromUser(userId, groupId);
    }
}
