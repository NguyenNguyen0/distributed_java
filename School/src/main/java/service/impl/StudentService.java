package service.impl;

import dao.StudentDAO;
import models.Student;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class StudentService extends GenericService<models.Student, Integer> implements service.IStudentService {
    private StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) throws RemoteException {
        super(studentDAO);
        this.studentDAO = studentDAO;
    }

    @Override
    public Map<Student, Double> getAverageScoreOfStudents() throws RemoteException {
        return studentDAO.getAverageScoreOfStudents();
    }

    @Override
    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) throws RemoteException {
        return studentDAO.listStudentsStudyingCourseWithHighestScore(courseName);
    }
}
