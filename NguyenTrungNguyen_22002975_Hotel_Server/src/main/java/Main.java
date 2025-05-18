import dao.RoomDAO;

public class Main {
    public static void main(String[] args) {
        RoomDAO roomDAO = new RoomDAO("nguyentrungnguyen22002975");
        System.out.println("The most booked room: " + roomDAO.getTheMostBookingRoom());
        System.out.println("The booked room by customer: " + roomDAO.getCustomerBookedRoom("C004"));
        System.out.println("The room type booking count: " + roomDAO.getRoomTypeBookingCount());
    }
}
