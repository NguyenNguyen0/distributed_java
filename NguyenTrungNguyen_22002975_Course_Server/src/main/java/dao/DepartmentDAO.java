package dao;

import model.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentDAO extends GenericDAO<Department, Integer>{
//    Departments without students
//+ listDepartmentsWithoutStudents(): List<Department>

    List<Department> listDepartmentsWithoutStudents();

//Calculate the number of students in each department, the result is decreasing the number of students.
//+ getNumberOfStudentsByDepartment (): Map<Department, Long>
    Map<Department, Long> getNumberOfStudentsByDepartment ();

//    List<Student> listStudentsStudyingCourseWithHighestScore(String courseName);
}
