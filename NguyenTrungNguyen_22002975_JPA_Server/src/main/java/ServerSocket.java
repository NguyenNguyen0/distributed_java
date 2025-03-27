import dao.DepartmentDAO;
import dao.StudentDAO;

import java.io.*;
import java.net.Socket;

public class ServerSocket {
    public static void main(String[] args) {
        try (
                java.net.ServerSocket serverSocket = new java.net.ServerSocket(2975);
        ) {
            System.out.println("Server started on port 2975...");
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ServerThread(socket));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ServerThread implements Runnable {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String clientAddress = socket.getInetAddress().getHostAddress();
        System.out.println("Accepted connection from " + clientAddress);
        try (
            var in = new DataInputStream(socket.getInputStream());
            var out = new DataOutputStream(socket.getOutputStream());
            var objectIn = new ObjectInputStream(in);
            var objectOut = new ObjectOutputStream(out);
        ) {
            String option = in.readUTF();

            switch (option) {
                case "1" -> {
                    System.out.println("== List Departments Without Students ==");
                    DepartmentDAO departmentDAO = new DepartmentDAO();
                    objectOut.writeObject(departmentDAO.listDepartmentsWithoutStudents());
                    System.out.println("Departments without students sent to client");
                }
                case "2" -> {
                    System.out.println("== No Of Students By Department ==");
                    DepartmentDAO departmentDAO = new DepartmentDAO();
                    objectOut.writeObject(departmentDAO.getNumberOfStudentsByDepartment());
                    System.out.println("Number of students sent to client");
                }

                case "3" -> {
                    System.out.println("== Get Average Score Of Students ==");
                    StudentDAO studentDAO = new StudentDAO();
                    objectOut.writeObject(studentDAO.getAverageScoreOfStudents());
                    System.out.println("Average score of students sent to client");
                }

                case "4" -> {
                    System.out.println("== List Students Studying Course With Highest Score ==");
                    StudentDAO studentDAO = new StudentDAO();
                    String courseName = in.readUTF();
                    objectOut.writeObject(studentDAO.listStudentsStudyingCourseWithHighestScore(courseName));
                    System.out.println("Students With Highest Score sent to client");
                }

                default -> System.out.println("Unknown option: " + option);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
