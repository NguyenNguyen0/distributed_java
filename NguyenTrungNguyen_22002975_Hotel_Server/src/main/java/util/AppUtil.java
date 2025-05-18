package util;

import model.Room;
import model.RoomType;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

public class AppUtil {
    public static Driver getDiriver() {
        String host = "neo4j://localhost";
        String user = "neo4j";
        String password = "12345678";
        return GraphDatabase.driver(host, AuthTokens.basic(user, password));
    }

    public static Room nodeToRoom (Node node) {
        return new Room(
            node.get("room_id").asString(),
            RoomType.valueOf(node.get("type").asString()),
            node.get("capacity").asInt(),
            node.get("price").asDouble()
        );
    }
}
