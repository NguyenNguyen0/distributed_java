package util;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;


public class AppUtil {
    public static Driver getDriver() {
        final String dbUri = "neo4j://localhost";
        final String dbUser = "neo4j";
        final String dbPassword = "12345678";
        return GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));
    }
}
