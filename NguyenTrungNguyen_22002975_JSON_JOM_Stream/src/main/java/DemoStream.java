import model.Employee;
import util.EmployeeUtils2;

import java.io.File;
import java.util.List;

public class DemoStream {
    public static void main(String[] args) {
//        Employee employee = new Employee(100l, "lmao lmao", 12.3);
//        EmployeeUtils2.toJson(employee, new File("json/emp.json"));

//        List<Employee> employees = List.of(
//                new Employee(100l, "lmao lmao", 23.2),
//                new Employee(200l, "bruh lmao", 23.2),
//                new Employee(300l, "lmao bruh", 23.2),
//                new Employee(400l, "bruh bruh", 23.2)
//        );
//
//        System.out.printf(EmployeeUtils2.toJson(employees));

        System.out.println(EmployeeUtils2.fromJson(new File("json/emp.json")));
    }
}
