package service;

import model.Department;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface DepartmentService extends GenericService<Department, Integer> {
    List<Department> listDepartmentsWithoutStudents() throws RemoteException;

    //Calculate the number of students in each department, the result is decreasing the number of students.
//+ getNumberOfStudentsByDepartment (): Map<Department, Long>
    Map<Department, Long> getNumberOfStudentsByDepartment () throws RemoteException;
}
