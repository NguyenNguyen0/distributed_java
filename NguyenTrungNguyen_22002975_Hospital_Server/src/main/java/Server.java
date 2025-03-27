import dao.DoctorDAO;
import model.Doctor;
import util.AppUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(2975);
        ) {
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
        try (
            var in = new DataInputStream(client.getInputStream());
            var out = new DataOutputStream(client.getOutputStream());
            var objectIn = new ObjectInputStream(in);
            var objectOut = new ObjectOutputStream(out);
        ) {
            String option = in.readUTF();
            DoctorDAO doctorDAO = new DoctorDAO(AppUtil.getDriver(), "nguyen22002975");
            System.out.println("Option: " + option);
            switch (option) {
                case "1":
                    System.out.println("== Add Doctor ==");
                    Doctor doctor = (Doctor) objectIn.readObject();
                    if (doctorDAO.addDoctor(doctor)) {
                        out.writeUTF("Server: Doctor added successfully");
                    } else {
                        out.writeUTF("Server: Failed to add doctor");
                    }
                    break;
                case "2":
                    System.out.println("== No Of Doctors By Speciality ==");
                    String departmentName = in.readUTF();
                    Map<String, Long> doctorBySpeciality = doctorDAO.getNoOfDoctorsBySpeciality(departmentName);
                    objectOut.writeObject(doctorBySpeciality);
                    out.writeUTF("Server: No Of Doctors By Speciality sent");
                    break;
                case "3":
                    System.out.println("== List Doctors By Speciality ==");
                    String speciality = in.readUTF();
                    List<Doctor> doctors = doctorDAO.listDoctorsBySpeciality(speciality);
                    objectOut.writeObject(doctors);
                    out.writeUTF("Server: List Doctors By Speciality sent");
                    break;
                case "4":
                    System.out.println("== Update Diagnosis ==");
                    String patientId = in.readUTF();
                    String doctorId = in.readUTF();
                    String diagnosis = in.readUTF();
                    if (doctorDAO.updateDiagnosis(patientId, doctorId, diagnosis)) {
                        out.writeUTF("Server: Diagnosis updated successfully");
                    } else {
                        out.writeUTF("Server: Failed to update diagnosis");
                    }
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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