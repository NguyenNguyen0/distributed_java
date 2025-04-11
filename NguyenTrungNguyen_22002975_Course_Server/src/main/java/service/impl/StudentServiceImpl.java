package service.impl;

import dao.GenericDAO;
import dao.StudentDAO;
import dao.impl.GenericDAOImpl;
import model.Student;
import service.GenericService;
import service.StudentService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl extends GenericServiceImpl<Student, Integer> implements StudentService {
    private StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) throws RemoteException {
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
