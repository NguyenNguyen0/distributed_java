package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServerSocket {

    public static void main(String[] args) throws IOException {
        try (
            ServerSocket serverSocket = new ServerSocket(2975);
        ) {
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new ServerProcessing(clientSocket));
                thread.start();
            }
        }
    }
}

class ServerProcessing implements Runnable {
    private Socket clientSocket;
    public ServerProcessing(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        String clientHostName = clientSocket.getInetAddress().getHostName();
        System.out.println("Processing client " + clientHostName + " request");
        try (
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        ) {
            String message = in.readUTF();
            System.out.println("Client message: " + message);
            out.writeUTF("Server received: " + message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}