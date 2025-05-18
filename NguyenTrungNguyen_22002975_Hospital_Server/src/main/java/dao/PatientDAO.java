package dao;

import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;

public class PatientDAO {
    private final Driver driver;
    private final SessionConfig sessionConfig;

    public PatientDAO(Driver driver, String dbName) {
        this.driver = driver;
        this.sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
    }
}
