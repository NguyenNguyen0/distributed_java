package rmi.service;

import dao.DepartmentDAO;
import models.Department;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class DepartmentService extends UnicastRemoteObject implements rmi.DepartmentService {
    public DepartmentService() throws RemoteException {
    }

    @Override
    public List<Department> listDepartmentsWithoutStudents() throws RemoteException {
        DepartmentDAO dao = new DepartmentDAO();
        return dao.listDepartmentsWithoutStudents();
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsByDepartment() throws RemoteException {
        DepartmentDAO dao = new DepartmentDAO();
        return dao.getNumberOfStudentsByDepartment();
    }
}
