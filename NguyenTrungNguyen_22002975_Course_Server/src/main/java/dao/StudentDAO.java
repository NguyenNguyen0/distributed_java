package dao;

import model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface StudentDAO extends GenericDAO<Student, Integer> {
//    Calculate the average score of the students' courses
//            + getAverageScoreOfStudents(): Map<Student, Double>
    Map<Student, Double> getAverageScoreOfStudents();

//    Students studying the subject's name "Distributed Programming with Java Technology" have the highest
//    scores.
//+ listStudentsStudyingCourseWithHighestScore(courseName: String): List<Student>
    List<Student> listStudentsStudyingCourseWithHighestScore(String courseName);
}
