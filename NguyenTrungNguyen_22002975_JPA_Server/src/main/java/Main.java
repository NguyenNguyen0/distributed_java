import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.OnlineCourseDAO;
import dao.StudentDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.OnlineCourse;
import models.Student;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
//        DepartmentDAO departmentDAO = new DepartmentDAO();
//        System.out.println(departmentDAO.listDepartmentsWithoutStudents());
//        System.out.println(departmentDAO.getNumberOfStudentsByDepartment());
        StudentDAO studentDAO = new StudentDAO();
//        System.out.println(studentDAO.getAverageScoreOfStudents());
        System.out.println(studentDAO.listStudentsStudyingCourseWithHighestScore("Quantitative"));
    }
}
