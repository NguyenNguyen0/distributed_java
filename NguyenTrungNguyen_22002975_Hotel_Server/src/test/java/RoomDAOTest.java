import dao.RoomDAO;
import model.Room;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDAOTest {
    @Test
    public void testGetMostBookedRoom() {
        RoomDAO roomDAO = new RoomDAO("nguyentrungnguyen22002975");
        List<Room> mostBookedRoom = roomDAO.getTheMostBookingRoom();
        System.out.println("Most booked room: " + mostBookedRoom);
        assertNotNull(mostBookedRoom);
    }

    @Test
    public void testGetCustomerBookedRoom() {
        RoomDAO roomDAO = new RoomDAO("nguyentrungnguyen22002975");
        List<Room> customerBookedRoom = roomDAO.getCustomerBookedRoom("C004");
        System.out.println("Customer booked room: " + customerBookedRoom);
        assertNotNull(customerBookedRoom);
        List<Room> customerBookedRoom2 = roomDAO.getCustomerBookedRoom("123123123");
        assertTrue(customerBookedRoom2.isEmpty());
    }

    @Test
    public void testGetRoomTypeBookingCount() {
        RoomDAO roomDAO = new RoomDAO("nguyentrungnguyen22002975");
        Map<String, Integer> roomTypeBookingCount = roomDAO.getRoomTypeBookingCount();
        System.out.println("Room type booking count: " + roomTypeBookingCount);
        assertNotNull(roomTypeBookingCount);
    }
}
