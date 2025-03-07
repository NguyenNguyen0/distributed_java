package util;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//The streaming API
public class EmployeeUtils2 {
// Employee emp = new Employee(100l, "Le Lan", 32.5);
//{"emp_id":100,"name":"Le Lan","salary":32.5}
    public static String toJson(Employee emp){
        StringWriter sw = null;
        try(JsonGenerator gen = Json.createGenerator(sw = new StringWriter())){
            gen
                    .writeStartObject()
                    .write("emp_id",emp.getId())
                    .write("name",emp.getName())
                    .write("salary",emp.getSalary())
                    .writeEnd();
        }
        return sw.toString();
    }
    public static void toJson(Employee emp, File file) {
        String json = toJson(emp);
        try (JsonGenerator generator = Json.createGenerator(new FileWriter(file))) {
            generator
                    .writeStartObject()
                    .write("emp_id",emp.getId())
                    .write("name",emp.getName())
                    .write("salary",emp.getSalary())
                    .writeEnd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String toJson(List<Employee> emps){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        emps.forEach(employee -> builder.add(toJson(employee)));
        return String.valueOf(builder.build());
    }

// {"emp_id":100,"name":"Le Lan","salary":32.5}
    public static Employee fromJson(String jsonFile){//{}
        Employee emp = null;
        String keyName = "";
        try(JsonParser parser = Json.createParser(new FileReader(jsonFile))){
            while (parser.hasNext()){
                JsonParser.Event event = parser.next();
                switch (event){
                    case START_OBJECT -> emp = new Employee();
                    case KEY_NAME -> keyName = parser.getString();
                    case VALUE_NUMBER -> {
                        if("emp_id".equals(keyName))
                            emp.setId(parser.getLong());
                        else if("salary".equals(keyName))
                            emp.setSalary(parser.getBigDecimal().doubleValue());
                    }
                    case VALUE_STRING -> emp.setName(parser.getString());
                    case END_OBJECT -> {
                        return emp;
                    }
                    default -> {}
                }
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return emp;
    }
    public static List<Employee> fromJson(File jsonFile){//[]
        List<Employee> employees = null;
        Employee employee = null;
        String keyName = null;
        try (JsonParser parser = Json.createParser(new FileReader(jsonFile))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event){
                    case START_ARRAY -> employees = new ArrayList<>();
                    case START_OBJECT -> employee = new Employee();
                    case KEY_NAME -> keyName = parser.getString();
                    case VALUE_NUMBER -> {
                        if("emp_id".equals(keyName))
                            employee.setId(parser.getLong());
                        else if("salary".equals(keyName))
                            employee.setSalary(parser.getBigDecimal().doubleValue());
                    }
                    case VALUE_STRING -> employee.setName(parser.getString());
                    case END_OBJECT -> {
                        employees.add(employee);
                    }
                    case END_ARRAY -> {
                        return employees;
                    }
                    default -> {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
