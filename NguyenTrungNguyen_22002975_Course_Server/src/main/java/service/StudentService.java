package service;

import model.Student;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface StudentService extends GenericService<Student, Integer>{
    Map<Student, Double> getAverageScoreOfStudents() throws RemoteException;
    List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) throws RemoteException;
}
