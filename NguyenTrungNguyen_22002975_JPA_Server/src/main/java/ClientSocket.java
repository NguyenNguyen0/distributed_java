import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. List Departments Without Students");
            System.out.println("2. No Of Student By Department");
            System.out.println("3. Get Average Score Of Students");
            System.out.println("4. List Students Studying Course With Highest Score");
            System.out.println("5. Exit");
            System.out.println("Choose option: ");
            int option = sc.nextInt();

            if (option == 5) {
                return;
            }

            try (
                    var socket = new Socket("NGUYEN", 2975);
                    var out = new DataOutputStream(socket.getOutputStream());
                    var in = new DataInputStream(socket.getInputStream());
                    var outObj = new ObjectOutputStream(out);
                    var inObj = new ObjectInputStream(in);
            ) {
                switch (option) {
                    case 1 -> {
                        out.writeUTF("1");
                        out.flush();
                        System.out.println("== List Departments Without Students ==");
                        System.out.println(inObj.readObject());
                    }
                    case 2 -> {
                        out.writeUTF("2");
                        out.flush();
                        System.out.println("== No Of Students By Department ==");
                        System.out.println(inObj.readObject());
                    }
                    case 3 -> {
                        out.writeUTF("3");
                        out.flush();
                        System.out.println("== Get Average Score Of Students ==");
                        System.out.println(inObj.readObject());
                    }
                    case 4 -> {
                        out.writeUTF("4");
                        System.out.println("Enter course name: ");
                        String courseName = sc.next();
                        out.writeUTF(courseName);
                        out.flush();
                        System.out.println("== List Students Studying Course With Highest Score ==");
                        System.out.println(inObj.readObject());
                    }
                    default -> System.out.println("Unknown option: " + option);
                }
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
