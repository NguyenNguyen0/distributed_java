package dao;

import models.Department;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class DepartmentDAO extends GenericDAO<models.Department, Integer> {
    public DepartmentDAO() {
        super(models.Department.class);
    }

    public Map<Department, Long> getNumberOfStudentsByDepartment() {
        String jpql = "SELECT d, COUNT(DISTINCT sg.student) " +
                "FROM Department d " +
                "JOIN d.courses c " +
                "JOIN StudentGrade sg ON sg.course = c " +
                "GROUP BY d " +
                "ORDER BY COUNT(DISTINCT sg.student) DESC";
        List<Object[]> result = em.createQuery(jpql, Object[].class).getResultList();
        Map<Department, Long> map = new LinkedHashMap<>();
        for (Object[] objects : result) {
            Department department = (Department) objects[0];
            Long count = (Long) objects[1];
            map.put(department, count);
        }
        return map;
    }

    public List<Department> listDepartmentsWithoutStudents() {
        String jpql = "SELECT d FROM Department d WHERE d.id NOT IN (SELECT DISTINCT sg.course.department.id FROM StudentGrade sg)";
        return em.createQuery(jpql, Department.class).getResultList();
    }
}
