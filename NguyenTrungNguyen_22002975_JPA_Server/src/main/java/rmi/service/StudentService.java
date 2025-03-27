package rmi.service;

import dao.StudentDAO;
import models.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class StudentService extends UnicastRemoteObject implements rmi.StudentService {
    public StudentService() throws RemoteException {
    }

    @Override
    public Map<Student, Double> getAverageScoreOfStudents() throws RemoteException {
        StudentDAO dao = new StudentDAO();
        return dao.getAverageScoreOfStudents();
    }

    @Override
    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) throws RemoteException {
        StudentDAO dao = new StudentDAO();
        return dao.listStudentsStudyingCourseWithHighestScore(courseName);
    }
}
