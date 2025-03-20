package dao;

import model.Doctor;
import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorDAO {
    private final Driver driver;
    private final SessionConfig sessionConfig;

    public DoctorDAO(Driver driver, String dbName) {
        this.driver = driver;
        this.sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
    }

    public boolean addDoctor(Doctor doctor) {
        String query = "MERGE (doc:Doctor {ID: $id, Name: $name, Phone: $phone, Speciality: $speciality})\n";
        Map<String, Object> parameters = Map.of(
                "id", doctor.getId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "speciality", doctor.getSpeciality()
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

    public Map<String, Long> getNoOfDoctorsBySpeciality (String departmentName) {
        String query = "MATCH (dep:Department {name: $departmentName})<-[r:BELONG_TO]-(doc:Doctor)\n" +
                "WITH doc.Speciality AS speciality, count(doc) AS quantity\n" +
                "RETURN speciality, quantity;";
        Map<String, Object> parameters = Map.of("departmentName", departmentName);

        try (var session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> tx.run(query, parameters).stream().collect(
                Collectors.toMap(
                    record -> record.get("speciality").asString(),
                    record -> record.get("quantity").asLong()
                )
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Doctor> listDoctorsBySpeciality (String keyword) {
        String query = "CALL db.index.fulltext.queryNodes('doctor_speciality_index', $keyword) YIELD node AS doctor";
        Map<String, Object> parameters = Map.of("keyword", keyword);
        try (var session = driver.session(sessionConfig)) {
            return session .readTransaction(tx -> tx.run(query, parameters).stream().map(
                record -> new Doctor(
                    record.get("doctor").get("ID").asString(),
                    record.get("doctor").get("Name").asString(),
                    record.get("doctor").get("Phone").asString(),
                    record.get("doctor").get("Speciality").asString()
                )
            ).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateDiagnosis(String patientID, String doctorID, String newDiagnosis) {
        String query = "MATCH (pat:Patient {ID: $patientID})-[r:BE_TREATED]->(doc:Doctor {ID: $doctorID})\n" +
                "WHERE r.EndDate IS NULL\n" +
                "SET r.Diagnosis = $newDiagnosis;";

        Map<String, Object> parameters = Map.of(
                "patientID", patientID,
                "doctorID", doctorID,
                "newDiagnosis", newDiagnosis
        );

        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, parameters);
                return result.consume().counters().propertiesSet() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
