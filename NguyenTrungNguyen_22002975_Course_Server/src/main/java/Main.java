import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.impl.DepartmentDAOImpl;
import dao.impl.StudentDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Department;
import model.Student;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

//        DepartmentDAO departmentDAO = new DepartmentDAOImpl(Department.class);

//        System.out.println(departmentDAO.listDepartmentsWithoutStudents());

        StudentDAO studentDAO = new StudentDAOImpl(Student.class);
        System.out.println(studentDAO.listStudentsStudyingCourseWithHighestScore("Microeconomics"));

//        Student student = new Student();
//        student.setFirstName("Lan");
//        student.setLastName("Le");
//        student.setEnrollmentDate(LocalDateTime.of(2022, 1,1,0,0,0));
//
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//        em.persist(student);
//
//        tr.commit();
    }
}
