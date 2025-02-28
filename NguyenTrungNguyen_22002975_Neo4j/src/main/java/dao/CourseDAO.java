package dao;

import models.Course;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.SessionConfig;
import util.AppUtil;

import java.util.List;
import java.util.Map;

public class CourseDAO {
    private final Driver driver;
    private final SessionConfig sessionConfig;

    public CourseDAO(Driver driver, String dbName) {
        this.driver = driver;
        this.sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
    }

    public boolean addCourse(Course course) {
        String query = "CREATE (c:Course {course_id: $id, name: $name, hours: $hours})";
        Map<String, Object> parameters = Map.of(
                "id", course.getId(),
                "name", course.getName(),
                "hours", course.getHours()
        );

        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, parameters);
                return result.consume().counters().nodesCreated() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCourse(Course course) {
        String query = "MATCH (c:Course {course_id: $id}) SET c.name = $name, c.hours = $hours RETURN c;";
        Map<String, Object> parameters = Map.of(
                "id", course.getId(),
                "name", course.getName(),
                "hours", course.getHours()
        );

        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, parameters);
                return result.consume().counters().containsUpdates();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCourse(String courseId) {
        String query = "MATCH (c:Course {course_id: $id}) DETACH DELETE c;";
        Map<String, Object> parameters = Map.of("id", courseId);

        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, parameters);
                return result.consume().counters().nodesDeleted() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Course findCourseById(String courseId) {
        String query = "MATCH (c:Course {course_id: $id}) RETURN c LIMIT 1;";
        Map<String, Object> parameters = Map.of("id", courseId);

        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, parameters);
                var record = result.single();
                return AppUtil.toCourse(record.get("c").asNode());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Course> listCourse(int skip, int limit) {
        List<Course> courses = List.of();
        String query = "MATCH (c:Course) SKIP $skip LIMIT $limit RETURN c";
        Map<String, Object> parameters = Map.of("skip", skip, "limit", limit);

        try (var session = driver.session(sessionConfig)) {
            courses = session.executeWrite(tx -> {
                Result result = tx.run(query, parameters);
                return result.list(record -> AppUtil.toCourse(record.get("c").asNode()));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public List<Course> listCourseByDepartmentId(String departmentId) {
        String query = "MATCH (c:Course)-[r:BELONGS_TO]-(d:Department {department_id: $id}) RETURN c;";
        Map<String, Object> parameters = Map.of("id", departmentId);
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, parameters);
                return result.list(record -> AppUtil.toCourse(record.get("c").asNode()));
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteAllCourses() {
        String query = "MATCH (c:Course) DETACH DELETE c;";
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query);
                return result.consume().counters().nodesDeleted() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> listStudentInCourse(String courseId) {
        String query = "MATCH (s:Student)-[r:ENROLLED]->(c:Course {course_id: $id}) RETURN s.name;";
        Map<String, Object> parameters = Map.of("id", courseId);
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, parameters);
                return result.list(record -> record.get("s.name").asString());
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
