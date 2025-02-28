package dao;

import models.Course;
import models.Department;
import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;
import util.AppUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentDAO {
    private final Driver driver;
    private final SessionConfig sessionConfig;

    public DepartmentDAO(Driver driver, String database) {
        this.driver = driver;
        this.sessionConfig = SessionConfig.builder().withDatabase(database).build();
    }

    public boolean renameDepartment(String departmentId, String newName) {
        String query = "MATCH (d:Department {department_id: $departmentId}) SET d.name = $newName RETURN d";
        Map<String, Object> params = Map.of("departmentId", departmentId, "newName", newName);
        try (var session = driver.session(sessionConfig)) {
            return session.writeTransaction(tx -> {
                var result = tx.run(query, params);
                return result.consume().counters().containsUpdates();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addCourseToDepartment(String departmentId, Course course) {
        String query = "MERGE (c:Course {course_id: $courseId})\n" +
                "  ON CREATE SET c.name = $courseName, c.hours = $courseHours\n" +
                "WITH c\n" +
                "MATCH (d:Department {department_id: $departmentId})\n" +
                "MERGE (c)-[:BELONGS_TO]->(d);";
        Map<String, Object> params = Map.of(
                "departmentId", departmentId,
                "courseId", course.getId(),
                "courseName", course.getName(),
                "courseHours", course.getHours()
        );
        try (var session = driver.session(sessionConfig)) {
            return session.writeTransaction(tx -> {
                var result = tx.run(query, params);
                return result.consume().counters().containsUpdates();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Department> listDepartment() {
        String query = "MATCH (d:Department) RETURN d";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).list(record -> {
                var node = record.get("d").asNode();
                return AppUtil.toDepartment(node);
            }));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> listDepartmentDeans() {
        String query = "MATCH (d:Department) RETURN d.dean;";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).list(record -> record.get("d.dean").asString()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findDepartmentDean(String departmentId) {
        String query = "MATCH (d:Department {department_id: $departmentId}) RETURN d.dean;";
        Map<String, Object> params = Map.of("departmentId", departmentId);
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                var result = tx.run(query, params);
                if (result.hasNext()) {
                    return result.single().get("d.dean").asString();
                } else {
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> listCoursesInCSAndIE() {
        String query = "MATCH (c:Course)-[:BELONGS_TO]->(d:Department)\n" +
                "WHERE d.department_id IN ['CS', 'IE']\n" +
                "RETURN c;\n";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).list(record -> {
                var node = record.get("c").asNode();
                return AppUtil.toCourse(node);
            }));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<Department, Long> listDepartmentStudentCount() {
        String query = "MATCH (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d:Department)\n" +
                "RETURN d, COUNT(*) AS `Enrolled Student`;";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).stream().collect(
                    Collectors.toMap(
                            record -> AppUtil.toDepartment(record.get("d").asNode()),
                            record -> record.get("Enrolled Student").asLong()
                    )
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<Department, Long> listDepartmentStudentCountDescById() {
        String query = "MATCH (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d:Department)\n" +
                "RETURN d, COUNT(*) AS `Enrolled Student`\n" +
                "ORDER BY d.department_id;";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).stream().collect(
                    Collectors.toMap(
                            record -> AppUtil.toDepartment(record.get("d").asNode()),
                            record -> record.get("Enrolled Student").asLong(),
                            (a, b) -> a, LinkedHashMap::new
                    )
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<Department, Long> listDepartmentStudentCountDescByStudentCount() {
        String query = "MATCH (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d:Department)\n" +
                "RETURN d, COUNT(*) AS `Enrolled Student`\n" +
                "ORDER BY `Enrolled Student`;";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).stream().collect(
                    Collectors.toMap(
                            record -> AppUtil.toDepartment(record.get("d").asNode()),
                            record -> record.get("Enrolled Student").asLong(),
                            (a, b) -> a, LinkedHashMap::new
                    )
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> listDepartmentDeanWithoutStudent() {
        String query = "MATCH (d:Department)\n" +
                "WHERE NOT (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d)\n" +
                "RETURN d.dean;";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).list(record -> record.get("d.dean").asString()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<Department, Long> listDepartmentHasMostStudent() {
        String query = "MATCH (:Student)-[:ENROLLED]->(:Course)-[:BELONGS_TO]->(d:Department)\n" +
                "WITH d, COUNT(*) AS enrolled_count\n" +
                "WITH MAX(enrolled_count) AS max_enrolled, COLLECT({department: d, count: enrolled_count}) AS departments\n" +
                "UNWIND departments AS dept\n" +
                "WITH dept.department AS department, dept.count AS enrolled_count, max_enrolled\n" +
                "  WHERE enrolled_count = max_enrolled\n" +
                "RETURN department, enrolled_count;";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).stream().collect(
                    Collectors.toMap(
                            record -> AppUtil.toDepartment(record.get("department").asNode()),
                            record -> record.get("enrolled_count").asLong()
                    )
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
