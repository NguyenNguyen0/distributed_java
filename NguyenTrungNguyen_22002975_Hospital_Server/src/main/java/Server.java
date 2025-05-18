import dao.DoctorDAO;
import model.Doctor;
import util.AppUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2975)) {
            System.out.println("Server started on port 2975");
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
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("Accepted connection from " + clientAddress);
        try {
            // Create ObjectOutputStream first to avoid deadlock
            var objectOut = new ObjectOutputStream(client.getOutputStream());
            var objectIn = new ObjectInputStream(client.getInputStream());

            String option = (String) objectIn.readObject();
            DoctorDAO doctorDAO = new DoctorDAO(AppUtil.getDriver(), "nguyen22002975");
            System.out.println("Option: " + option);

            switch (option) {
                case "1":
                    System.out.println("== Add Doctor ==");
                    Doctor doctor = (Doctor) objectIn.readObject();
                    if (doctorDAO.addDoctor(doctor)) {
                        objectOut.writeObject("Server: Doctor added successfully");
                    } else {
                        objectOut.writeObject("Server: Failed to add doctor");
                    }
                    break;
                case "2":
                    System.out.println("== No Of Doctors By Speciality ==");
                    String departmentName = (String) objectIn.readObject();
                    Map<String, Long> doctorBySpeciality = doctorDAO.getNoOfDoctorsBySpeciality(departmentName);
                    objectOut.writeObject(doctorBySpeciality);
                    objectOut.writeObject("Server: No Of Doctors By Speciality sent");
                    break;
                case "3":
                    System.out.println("== List Doctors By Speciality ==");
                    String speciality = (String) objectIn.readObject();
                    List<Doctor> doctors = doctorDAO.listDoctorsBySpeciality(speciality);
                    objectOut.writeObject(doctors);
                    objectOut.writeObject("Server: List Doctors By Speciality sent");
                    break;
                case "4":
                    System.out.println("== Update Diagnosis ==");
                    String patientId = (String) objectIn.readObject();
                    String doctorId = (String) objectIn.readObject();
                    String diagnosis = (String) objectIn.readObject();
                    if (doctorDAO.updateDiagnosis(patientId, doctorId, diagnosis)) {
                        objectOut.writeObject("Server: Diagnosis updated successfully");
                    } else {
                        objectOut.writeObject("Server: Failed to update diagnosis");
                    }
                    break;
                case "5":
                    System.out.println("=== Delete Doctor ===");
                    String doctorIdToDelete = (String) objectIn.readObject();
                    if (doctorDAO.deleteDoctor(doctorIdToDelete)) {
                        objectOut.writeObject("Server: Doctor deleted successfully");
                    } else {
                        objectOut.writeObject("Server: Failed to delete doctor");
                    }
                    break;
                default:
                    System.out.println("Invalid option");
            }
            objectOut.flush();
        } catch (SocketException se) {
            System.err.println("Connection with client " + clientAddress + " terminated: " + se.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error processing client " + clientAddress + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}