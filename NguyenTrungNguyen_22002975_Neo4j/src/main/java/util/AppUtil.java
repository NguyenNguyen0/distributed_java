package util;

import models.Course;
import models.Department;
import models.Student;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

public class AppUtil {
    public static Driver getDriver() {
        final String dbUri = "neo4j://localhost";
        final String dbUser = "neo4j";
        final String dbPassword = "123456789";
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
}
