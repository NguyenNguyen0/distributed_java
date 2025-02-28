package dao;

import models.Course;
import models.Enrollment;
import models.Student;
import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;
import util.AppUtil;

import java.util.List;
import java.util.Map;

public class StudentDAO {
    private final Driver driver;
    private final SessionConfig sessionConfig;

    public StudentDAO(Driver driver, String database) {
        this.driver = driver;
        this.sessionConfig = SessionConfig.builder().withDatabase(database).build();
    }


    public List<Student> listStudent(int skip, int limit) {
        String query = "MATCH (s:Student) RETURN s SKIP $skip LIMIT $limit";
        Map<String, Object> params = Map.of("skip", skip, "limit", limit);
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query, params).list(record -> {
                var student = record.get("s").asNode();
                return AppUtil.toStudent(student);
            }));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student findStudentById(String studentId) {
        String query = "MATCH (s:Student {student_id: $studentId}) RETURN s";
        Map<String, Object> params = Map.of("studentId", studentId);
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                var result = tx.run(query, params);
                if (result.hasNext()) {
                    return AppUtil.toStudent(result.single().get("s").asNode());
                } else {
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> listStudentHasGpaOverThreePointTwo() {
        String query = "MATCH (s:Student) WHERE s.gpa >= 3.2\n" +
                "RETURN s ORDER BY s.gpa DESC;";
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query).list(record -> {
                var student = record.get("s").asNode();
                return AppUtil.toStudent(student);
            }));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean enrollCourse(String studentId, String courseId){
        String query = "MATCH (s:Student {student_id: $student_id}), (c:Course {course_id: $course_id})\n" +
                "MERGE (s)-[:ENROLLED]->(c);";

        Map<String, Object> params = Map.of(
                "student_id", studentId,
                "course_id", courseId
        );

        try (var session = driver.session(sessionConfig)) {
            var result =  session.run(query, params);
            return result.consume().counters().relationshipsCreated() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unEnrollCourse(String studentId, String courseId){
        String query = "MATCH (s:Student {student_id: $student_id})-[e:ENROLLED]->(c:Course {course_id: $course_id})\n" +
                "DELETE e;";

        Map<String, Object> params = Map.of(
                "student_id", studentId,
                "course_id", courseId
        );

        try (var session = driver.session(sessionConfig)) {
            var result =  session.run(query, params);
            return result.consume().counters().relationshipsDeleted() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEnrollment(String studentId, String courseId, double grade){
        String query = "MATCH (s:Student {student_id: $student_id})-[e:ENROLLED]->(c:Course {course_id: $course_id})\n" +
                "SET e.grade = $grade;";

        Map<String, Object> params = Map.of(
                "student_id", studentId,
                "course_id", courseId,
                "grade", grade
        );

        try (var session = driver.session(sessionConfig)) {
            var result = session.run(query, params);
            return result.consume().counters().propertiesSet() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }


    public Enrollment findEnrollment(String studentId, String courseId){
        String query = "MATCH (s:Student {student_id: $student_id})-[e:ENROLLED]->(c:Course {course_id: $course_id})\n" +
                "RETURN s, e, c;";

        Map<String, Object> params = Map.of(
                "student_id", studentId,
                "course_id", courseId
        );
        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                var result = tx.run(query, params);
                if (result.hasNext()) {
                    var record = result.single();
                    var student = AppUtil.toObject(record.get("s").asNode(), Student.class);
                    var course = AppUtil.toObject(record.get("c").asNode(), Course.class);
                    var enrollment = record.get("e").asRelationship();
                    return new Enrollment(student, course, enrollment.get("grade").asDouble());
                } else {
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}