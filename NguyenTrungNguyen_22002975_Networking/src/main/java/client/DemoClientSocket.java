package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DemoClientSocket {
    public static void main(String[] args) throws IOException {
        System.out.println("Client is running...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter message to send to server: ");
            String message = scanner.nextLine();
            try (
                Socket socket = new Socket("NGUYEN", 2975);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
            ) {
                System.out.println("Connected to server...");
                out.writeUTF(message);
                System.out.println("Server response: " + in.readUTF());
            }
        }
    }
}
