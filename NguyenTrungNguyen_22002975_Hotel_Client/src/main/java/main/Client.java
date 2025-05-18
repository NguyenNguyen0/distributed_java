package main;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("========MENU========");
            System.out.println("1. Get the most booked room");
            System.out.println("2. Get the booked room by customer");
            System.out.println("3. Get the room type booking count");
            System.out.println("5. Exit");
            System.out.println("=====================");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            if (option == 5) {
                break;
            }

            try (
                    var socket = new Socket("NGUYEN", 2975);
                    var outputStream = new DataOutputStream(socket.getOutputStream());
                    var inputStream = new DataInputStream(socket.getInputStream());
                    var objectOutputStream = new ObjectOutputStream(outputStream);
                    var objectInputStream = new ObjectInputStream(inputStream);
            ) {
                switch (option) {
                    case 1 -> {
                        System.out.println("== Request the most booked room ==");
                        outputStream.writeUTF("1");
                        var mostBookedRoom = objectInputStream.readObject();
                        System.out.println("Most booked room: " + mostBookedRoom);
                    }

                    case 2 -> {
                        System.out.println("== Request the booked room by customer ==");
                        System.out.print("Enter customer ID: ");
                        String customerId = scanner.next();
                        outputStream.writeUTF("2");
                        outputStream.writeUTF(customerId);
                        var bookedRoom = objectInputStream.readObject();
                        System.out.println("Booked room by customer: " + bookedRoom);
                    }

                    case 3 -> {
                        System.out.println("== Request the room type booking count ==");
                        outputStream.writeUTF("3");
                        var roomTypeBookingCount = objectInputStream.readObject();
                        System.out.println("Room type booking count: " + roomTypeBookingCount);
                    }

                    default -> {
                        System.out.println("Invalid option. Please try again.");
                    }
                }
            } catch (UnknownHostException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        scanner.close();
    }
}
