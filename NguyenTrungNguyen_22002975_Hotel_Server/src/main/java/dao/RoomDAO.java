package dao;

import model.Room;
import model.RoomType;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import util.AppUtil;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RoomDAO {
    public final Driver driver;
    public final SessionConfig config;
    public final Session session;

    public RoomDAO(String dbName) {
        driver = AppUtil.getDiriver();
        config = SessionConfig.builder()
                .withDatabase(dbName)
                .build();
        session = driver.session(config);
    }

    public List<Room> getTheMostBookingRoom() {
        String cypher = "MATCH (r:Room)<-[:BOOKED]-(b:Booking) " +
                "RETURN r, COUNT(b) AS bookingCount " +
                "ORDER BY bookingCount DESC " +
                "LIMIT 10";
        return session.run(cypher).list(record -> AppUtil.nodeToRoom(record.get("r").asNode()));
    }

    public List<Room> getCustomerBookedRoom(String customerId) {
        String cypher = "MATCH (c:Customer {customer_id: $customerId})-[:MADE_BY]-()-[:BOOKED]->(r:Room) " +
                "RETURN r";

        Map<String, Object> params = Map.of("customerId", customerId);

        return session.run(cypher, params).list(record -> AppUtil.nodeToRoom(record.get("r").asNode()));
    }

    public Map<String, Integer> getRoomTypeBookingCount() {
        String cypher = "MATCH (b:Booking)-[:BOOKED]->(r:Room)\n" +
                "RETURN r.type AS roomType, COUNT(b) AS bookingCount";

        return session.run(cypher).list(record -> {
            String roomType = record.get("roomType").asString();
            int bookingCount = record.get("bookingCount").asInt();
            return Map.entry(roomType, bookingCount);
        }).stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
}
