package dao.impl;

import dao.DepartmentDAO;
import jakarta.persistence.EntityManager;
import model.Department;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentDAOImpl extends GenericDAOImpl<Department, Integer> implements DepartmentDAO {

    public DepartmentDAOImpl(Class<Department> clazz) {
        super(clazz);
    }

    public DepartmentDAOImpl(EntityManager em, Class<Department> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Department> listDepartmentsWithoutStudents() {
        String query = "select d from Department d " +
                "where d.id not in (" +
                "select distinct c.id from" +
                " Course c inner join StudentGrade sg on c.id = sg.id)";

        return em.createQuery(query, Department.class)
                .getResultList();
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsByDepartment() {
        String query = "select c.department, count(sg.student) as number " +
                "from Course c inner join StudentGrade sg " +
                "on c.id = sg.course.id " +
                "group by c.department " +
                "order by number desc ";

        return em.createQuery(query, Object[].class)
                .getResultList()
                .stream() //xu ly tuan tu
                .collect(Collectors.toMap(
                        result -> (Department) result[0],
                        result -> (Long) result[1],
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
