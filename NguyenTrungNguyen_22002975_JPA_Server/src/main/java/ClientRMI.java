import rmi.DepartmentService;
import rmi.StudentService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            Context context = new InitialContext();
            DepartmentService departmentService = (DepartmentService) context.lookup("rmi://localhost:2975/departmentService");
            StudentService studentService = (StudentService) context.lookup("rmi://localhost:2975/studentService");

            Scanner scanner = new Scanner(System.in);
            String message = "";
            while (true) {
                System.out.println("1. List Departments Without Students");
                System.out.println("2. No Of Student By Department");
                System.out.println("3. Get Average Score Of Students");
                System.out.println("4. List Students Studying Course With Highest Score");
                System.out.println("5. Exit");
                System.out.println("Choose option: ");
                int option = scanner.nextInt();

                if (option == 5) {
                    return;
                }

                switch (option) {
                    case 1 -> {
                        System.out.println("== List Departments Without Students ==");
                        System.out.println(departmentService.listDepartmentsWithoutStudents());
                    }
                    case 2 -> {
                        System.out.println("== No Of Students By Department ==");
                        System.out.println(departmentService.getNumberOfStudentsByDepartment());
                    }
                    case 3 -> {
                        System.out.println("== Get Average Score Of Students ==");
                        System.out.println(studentService.getAverageScoreOfStudents());
                    }
                    case 4 -> {
                        System.out.println("Enter course name: ");
                        String courseName = scanner.next();
                        System.out.println("== List Students Studying Course With Highest Score ==");
                        System.out.println(studentService.listStudentsStudyingCourseWithHighestScore(courseName));
                    }
                    default -> System.out.println("Unknown option: " + option);
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
