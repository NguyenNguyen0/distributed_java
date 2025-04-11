package service.impl;

import dao.DepartmentDAO;
import dao.GenericDAO;
import model.Department;
import service.DepartmentService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class DepartmentServiceImpl extends GenericServiceImpl<Department, Integer> implements DepartmentService {

    protected DepartmentDAO departmentDAO;
    public DepartmentServiceImpl(DepartmentDAO departmentDAO) throws RemoteException {
        super(departmentDAO);
        this.departmentDAO = departmentDAO;
    }

    @Override
    public List<Department> listDepartmentsWithoutStudents() throws RemoteException {
        return departmentDAO.listDepartmentsWithoutStudents();
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsByDepartment() throws RemoteException {
        return departmentDAO.getNumberOfStudentsByDepartment();
    }
}
