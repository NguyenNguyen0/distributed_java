import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import model.Employee;

import java.io.FileInputStream;
import java.io.InputStream;

public class DecodeJson {
    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream("json/emp.json");
        JsonReader reader = Json.createReader(in);
        JsonObject jo = ((JsonReader) reader).readObject();
        JsonNumber id = jo.getJsonNumber("id");
        String name = jo.getString("name");
//        Distributed Programming With Java -Lab FIT IUH
//        Department of Software Engineering 14
        JsonNumber sal = jo.getJsonNumber("salary");
        Employee emp = new Employee(
                id.longValue(),
                name,
                sal.doubleValue()
        );
        System.out.println(emp);
    }
}
