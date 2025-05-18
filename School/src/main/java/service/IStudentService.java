package service;

import models.Student;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IStudentService extends IGenericService<Student, Integer> {
    public Map<Student, Double> getAverageScoreOfStudents() throws RemoteException;
    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) throws RemoteException;
}
