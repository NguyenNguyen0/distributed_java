package service.impl;

import dao.DepartmentDAO;
import models.Department;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class DepartmentService extends GenericService<models.Department, Integer> implements service.IDepartmentService {
    private DepartmentDAO departmentDAO;

    public DepartmentService(DepartmentDAO departmentDAO) throws RemoteException {
        super(departmentDAO);
        this.departmentDAO = departmentDAO;
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsByDepartment() throws RemoteException {
        return departmentDAO.getNumberOfStudentsByDepartment();
    }

    @Override
    public List<Department> listDepartmentsWithoutStudents() throws RemoteException {
        return departmentDAO.listDepartmentsWithoutStudents();
    }
}
