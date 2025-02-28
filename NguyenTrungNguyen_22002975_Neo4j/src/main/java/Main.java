import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import util.AppUtil;

public class Main {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO(
                AppUtil.getDriver(),
                "nguyentrungnguyen22002975"
        );
        StudentDAO studentDAO = new StudentDAO(
                AppUtil.getDriver(),
                "nguyentrungnguyen22002975"
        );
        DepartmentDAO departmentDAO = new DepartmentDAO(
                AppUtil.getDriver(),
                "nguyentrungnguyen22002975"
        );


//        studentDAO.listStudent(0, 5).forEach(System.out::println);
//        System.out.println(studentDAO.findStudentById("33"));
//        System.out.println(courseDAO.listCourseByDepartmentId("CS"));
//        System.out.println(departmentDAO.renameDepartment("Math", "Mathematics"));
//        System.out.println(departmentDAO.renameDepartment("Music", "Rock n Roll"));
//        System.out.println(departmentDAO.addCourseToDepartment("IE", new Course("IE202", "Simulation", 3)));
//        System.out.println(courseDAO.deleteAllCourses());
//        departmentDAO.listDepartment().forEach(System.out::println);
//        departmentDAO.listDepartmentDeans().forEach(System.out::println);
//        System.out.println(departmentDAO.findDepartmentDean("CS"));
//        departmentDAO.listCoursesInCSAndIE().forEach(System.out::println);
//        courseDAO.listStudentInCourse("CS101").forEach(System.out::println);
//        departmentDAO.listDepartmentStudentCount().forEach((department, count) -> {
//            System.out.println(department + " has " + count + " students");
//        });
//        departmentDAO.listDepartmentStudentCountDescById().forEach((department, count) -> {
//            System.out.println(department + " has " + count + " students");
//        });
//        departmentDAO.listDepartmentStudentCountDescByStudentCount().forEach((department, count) -> {
//            System.out.println(department + " has " + count + " students");
//        });

//        departmentDAO.listDepartmentDeanWithoutStudent().forEach(System.out::println);
//        departmentDAO.listDepartmentHasMostStudent().forEach((department, count) -> {
//            System.out.println(department + " has " + count + " students");
//        });
//        studentDAO.listStudentHasGpaOverThreePointTwo().forEach(System.out::println);

//        System.out.println(studentDAO.enrollCourse("12", "MA301"));
//        System.out.println(studentDAO.unEnrollCourse("12", "MA301"));
//        System.out.println(studentDAO.updateEnrollment("33", "MA201", 9));
//        System.out.println(studentDAO.findEnrollment("33", "MA201"));

//        var newJava = new Course("JAVA02", "Advance Java Programing", 40);
//        if (courseDAO.addCourse(newJava)) {
//            System.out.println("Course added successfully");
//        } else {
//            System.out.println("Failed to add course");
//        }
//
//        Course java = courseDAO.findCourseById("JAVA02");
//        if (java != null) {
//            System.out.println(java);
//        } else {
//            System.out.println("Course not found");
//        }
//
//        for (var course : courseDAO.listCourse(0, 5)) {
//            System.out.println(course);
//        }
//
//        System.out.println(courseDAO.updateCourse(new Course("JAVA02", "Advanced Jav Programming", 50)));
//        System.out.println(courseDAO.findCourseById("JAVA02"));
//        System.out.println(courseDAO.deleteCourse("JAVA02"));

    }
}
