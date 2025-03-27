package dao;

import models.Department;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentDAO extends GenericDAO<Department> {
    public DepartmentDAO() {
        super(Department.class);
    }

    public Map<Department, Long> getNumberOfStudentsByDepartment() {
        List<Object[]> results = em.createQuery(
                        "SELECT c.department, COUNT(DISTINCT sg.student) FROM StudentGrade sg " +
                                "JOIN sg.course c GROUP BY c.department ORDER BY COUNT(DISTINCT sg.student) DESC", Object[].class)
                .getResultList();

        return results.stream().collect(Collectors.toMap(
                result -> (Department) result[0],
                result -> (Long) result[1]
        ));
    }

    public List<Department> listDepartmentsWithoutStudents() {
        return em.createQuery(
            "SELECT d FROM Department d WHERE d.id NOT IN " +
            "(SELECT DISTINCT c.department.id FROM Course c " +
            "JOIN StudentGrade sg ON sg.course.id = c.id)", Department.class)
            .getResultList();
    }
}