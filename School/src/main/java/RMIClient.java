import service.IDepartmentService;
import service.IStudentService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        IStudentService studentService = (IStudentService) context.lookup("rmi://NGUYEN:2975/studentService");
        IDepartmentService departmentService = (IDepartmentService) context.lookup("rmi://NGUYEN:2975/departmentService");

        System.out.println( "Student average score: " + studentService.getAverageScoreOfStudents() );
        System.out.println( "Student highest score: " + studentService.listStudentsStudyingCourseWithHighestScore("Composition") );
        System.out.println( "Departments without students: " + departmentService.listDepartmentsWithoutStudents() );
        System.out.println( "Number of students by department: " + departmentService.getNumberOfStudentsByDepartment() );
    }
}
