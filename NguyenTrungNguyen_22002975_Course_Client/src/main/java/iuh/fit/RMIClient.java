package iuh.fit;

import service.DepartmentService;
import service.StudentService;

import javax.naming.Context;
import javax.naming.InitialContext;

public class RMIClient {
    public static void main(String[] args) throws Exception{

        Context context = new InitialContext();

        StudentService studentService = (StudentService) context.lookup("rmi://H51M06:2975/studentService");
        DepartmentService departmentService = (DepartmentService) context.lookup("rmi://H51M06:2975/departmentService");

        departmentService.getNumberOfStudentsByDepartment()
                .entrySet()
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println("Number of students: " + entry.getValue());
                });


//
//        studentService.getAll()
//                .forEach(st -> System.out.println(st));
//
//        studentService.getAverageScoreOfStudents()
//                .entrySet()
//                .forEach(entry -> {
//                    System.out.println(entry.getKey());
//                    System.out.println("Avg of grades: " + entry.getValue());
//                });
//
    }
}
