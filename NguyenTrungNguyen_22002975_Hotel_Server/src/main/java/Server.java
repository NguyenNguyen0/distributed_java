import dao.RoomDAO;
import model.Room;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(2975);
        ) {
            System.out.println("Server is running on port 2975...");
            System.out.println("Server address: " + serverSocket.getLocalSocketAddress());
            while (true) {
                Socket client = serverSocket.accept();
                Thread thread = new Thread(new ServerThread(client));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class ServerThread implements Runnable {
    private Socket client;
    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        RoomDAO roomDAO = new RoomDAO("nguyentrungnguyen22002975");
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("Client connected: " + clientAddress);

        try (
                var inputStream = new DataInputStream(client.getInputStream());
                var outputStream = new DataOutputStream(client.getOutputStream());
                var objectInputStream = new ObjectInputStream(inputStream);
                var objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            String option = inputStream.readUTF();
            switch (option) {
                case "1" -> {
                    System.out.println("== Handle the most booked room ==");
                    List<Room> mostBookedRoom = roomDAO.getTheMostBookingRoom();
                    objectOutputStream.writeObject(mostBookedRoom);
                }
                case "2" -> {
                    System.out.println("== Handle the booked room by customer ==");
                    String customerId = inputStream.readUTF();
                    List<Room> bookedRoom = roomDAO.getCustomerBookedRoom(customerId);
                    objectOutputStream.writeObject(bookedRoom);
                }
                case "3" -> {
                    System.out.println("== Handle the room type booking count ==");
                    Map<String, Integer> roomTypeBookingCount = roomDAO.getRoomTypeBookingCount();
                    objectOutputStream.writeObject(roomTypeBookingCount);
                }

                default -> {
                    System.out.println("Invalid option");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
