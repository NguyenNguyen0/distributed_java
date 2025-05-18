package dao;

import models.Department;
import models.Student;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO extends GenericDAO<models.Student, Integer>{
    public StudentDAO() {
        super(models.Student.class);
    }

    public Map<Student, Double> getAverageScoreOfStudents() {
        String jpql = "SELECT s, AVG(sg.grade) FROM Student s JOIN s.studentGrades sg GROUP BY s order by AVG(sg.grade) DESC";

        List<Object[]> result = em.createQuery(jpql, Object[].class).getResultList();
        Map<Student, Double> map = new LinkedHashMap<>();
        for (Object[] objects : result) {
            Student student = (Student) objects[0];
            Double count = (Double) objects[1];
            map.put(student, count);
        }
        return map;
    }

    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) {
        String jpql = "SELECT s FROM Student s JOIN s.studentGrades sg WHERE sg.course.title = :courseName GROUP BY s ORDER BY AVG(sg.grade) DESC";
        return em.createQuery(jpql, Student.class).setParameter("courseName", courseName).getResultList();
    }
}
