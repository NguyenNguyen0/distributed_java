package service;

import model.User;

import java.rmi.RemoteException;

public interface IUserService extends IGenericService<User, Integer> {
    User getByUsername(String username) throws RemoteException;
    User getByEmail(String email) throws RemoteException;
    boolean isUsernameExists(String username) throws RemoteException;
    boolean isEmailExists(String email) throws RemoteException;
    void addGroupToUser(int userId, int groupId) throws RemoteException;
    void removeGroupFromUser(int userId, int groupId) throws RemoteException;
}
