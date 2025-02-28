package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Course;
import models.Department;
import models.Student;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import java.util.Map;

public class AppUtil {
    private static final Gson GSON = new Gson();

    public static Driver getDriver() {
        final String dbUri = "neo4j://localhost";
        final String dbUser = "neo4j";
        final String dbPassword = "12345678";
        return GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));
    }

    public static Course toCourse(Node node) {
        return new Course(
                node.get("course_id").asString(),
                node.get("name").asString(),
                node.get("hours").asInt()
        );
    }

    public static Student toStudent(Node node) {
        return new Student(
                node.get("student_id").asString(),
                node.get("name").asString(),
                node.get("gpa").asDouble()
        );
    }

    public static Department toDepartment(Node node) {
        return new Department(
                node.get("department_id").asString(),
                node.get("name").asString(),
                node.get("dean").asString(),
                node.get("building").asString(),
                node.get("room").asInt()
        );
    }

    public static <T> T toObject(Node node, Class<T> cls) {
        Map<String, Object> map = node.asMap();
        String json = GSON.toJson(map);
        return GSON.fromJson(json, cls);
    }

    public static <T> Map<String, Object> asMap(T cls){
        String json = GSON.toJson(cls);
        return GSON.fromJson(json,  new TypeToken<Map<String, Object>>(){}  );
    }
}
