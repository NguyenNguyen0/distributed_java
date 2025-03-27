package rmi;

import models.Department;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface DepartmentService extends Remote {
    List<Department> listDepartmentsWithoutStudents() throws RemoteException;
    Map<Department, Long> getNumberOfStudentsByDepartment() throws RemoteException;
}
