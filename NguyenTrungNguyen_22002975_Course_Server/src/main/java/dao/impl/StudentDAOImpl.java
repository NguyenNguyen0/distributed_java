package dao.impl;

import dao.StudentDAO;
import jakarta.persistence.EntityManager;
import model.Course;
import model.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDAOImpl extends GenericDAOImpl<Student, Integer> implements StudentDAO {

    public StudentDAOImpl(Class<Student> clazz) {
        super(clazz);
    }

    public StudentDAOImpl(EntityManager em, Class<Student> clazz) {
        super(em, clazz);
    }

    //JPQL
    @Override
    public Map<Student, Double> getAverageScoreOfStudents() {
        String query = "Select sg.student, avg(sg.grade) as avgGrade " +
                "from  StudentGrade sg " +
                "where sg.grade is not null " +
                "group by sg.student ";
        return em.createQuery(query, Object[].class)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        result -> (Student) result[0],
                        result -> (Double) result[1]
                ));
    }

    @Override
    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) {
        // Tìm khóa học có điểm cao nhất
        String courseQuery = "select sg.course from StudentGrade sg " +
                "where sg.course.title = :courseName " +
                "group by sg.course " +
                "having max(sg.grade) = (select max(sg2.grade) from StudentGrade sg2 where sg2.course.title = :courseName)";

        Course courseWithHighestScore = (Course) em.createQuery(courseQuery)
                .setParameter("courseName", courseName)
                .getSingleResult();

        // Lấy danh sách sinh viên học khóa học có điểm cao nhất
        String studentQuery = "select sg.student from StudentGrade sg " +
                "where sg.course = :courseWithHighestScore " +
                "and sg.grade = (select max(sg2.grade) from StudentGrade sg2 where sg2.course = :courseWithHighestScore)";

        return em.createQuery(studentQuery, Student.class)
                .setParameter("courseWithHighestScore", courseWithHighestScore)
                .getResultList();
    }

}
