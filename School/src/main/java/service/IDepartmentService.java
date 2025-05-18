package service;

import models.Department;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IDepartmentService extends IGenericService<models.Department, Integer> {
    public Map<Department, Long> getNumberOfStudentsByDepartment() throws RemoteException;
    public List<Department> listDepartmentsWithoutStudents() throws RemoteException;
}
