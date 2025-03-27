package rmi;

import models.Department;
import models.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface StudentService extends Remote {
    Map<Student, Double> getAverageScoreOfStudents() throws RemoteException;
    List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) throws RemoteException;
}
