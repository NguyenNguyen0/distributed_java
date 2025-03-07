import model.Employee;
import util.EmployeeUtils;

import java.io.File;
import java.util.List;

public class DemoJOM {
    public static void main(String[] args) {
        List<Employee> employeeList = List.of(
                new Employee(100l, "lmao lmao", 23.2),
                new Employee(200l, "bruh lmao", 23.2),
                new Employee(300l, "lmao bruh", 23.2),
                new Employee(400l, "bruh bruh", 23.2)
        );

        System.out.println(EmployeeUtils.toJson(employeeList));

        System.out.println(EmployeeUtils.fromJson(new File("json/emp.json")));
    }
}
