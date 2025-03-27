package dao;

import models.Student;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO() {
        super(Student.class);
    }

    public Map<Student, Double> getAverageScoreOfStudents() {
        List<Object[]> results = em.createQuery(
                        "SELECT sg.student, AVG(sg.grade) FROM StudentGrade sg GROUP BY sg.student ORDER BY AVG(sg.grade) DESC", Object[].class)
                .getResultList();
        return results.stream()
                .filter(result -> result[0] != null && result[1] != null)
                .collect(Collectors.toMap(
                result -> (Student) result[0],
                result -> (Double) result[1]
        ));
    }

    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) {
        Map<String, Object> parameters = Map.of("courseName", courseName);
        return em.createQuery(
                "SELECT sg.student FROM StudentGrade sg " +
                "WHERE sg.course.title = :courseName " +
                "AND sg.grade = (SELECT MAX(sg2.grade) FROM StudentGrade sg2 WHERE sg2.course.title = :courseName)", Student.class)
            .setParameter("courseName", courseName)
            .getResultList();
    }
}