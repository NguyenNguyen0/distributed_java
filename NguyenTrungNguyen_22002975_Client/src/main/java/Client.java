import model.Doctor;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (true) {
            System.out.println("1. Add Doctor");
            System.out.println("2. No Of Doctors By Speciality");
            System.out.println("3. List Doctors By Speciality");
            System.out.println("4. Update Diagnosis");
            System.out.println("5. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 5) {
                break;
            }

            try (
                var socket = new Socket("NGUYEN", 2975);
                var out = new DataOutputStream(socket.getOutputStream());
                var in = new DataInputStream(socket.getInputStream());
                var objectOut = new ObjectOutputStream(out);
                var objectIn = new ObjectInputStream(in);
            ) {
                switch (option) {
                    case 1:
                        System.out.println("== Add Doctor ==");
                        Doctor doctor = new Doctor();

                        System.out.println("Enter doctor ID: ");
                        doctor.setId(scanner.nextLine());
                        System.out.println("Enter doctor name: ");
                        doctor.setName(scanner.nextLine());
                        System.out.println("Enter doctor phone: ");
                        doctor.setPhone(scanner.nextLine());
                        System.out.println("Enter doctor speciality: ");
                        doctor.setSpeciality(scanner.nextLine());

                        out.writeUTF(String.valueOf(option));
                        objectOut.writeObject(doctor);
                        message = in.readUTF();
                        break;
                    case 2:
                        System.out.println("== No Of Doctors By Speciality ==");
                        System.out.println("Enter department name: ");
                        String departmentName = scanner.nextLine();

                        out.writeUTF(String.valueOf(option));
                        out.writeUTF(departmentName);
                        Map<String, Long> doctorBySpeciality = (Map<String, Long>) objectIn.readObject();
                        System.out.println(doctorBySpeciality);
                        message = in.readUTF();
                        break;
                    case 3:
                        System.out.println("== List Doctors By Speciality ==");
                        System.out.println("Enter speciality: ");
                        String speciality = scanner.nextLine();
                        out.writeUTF(String.valueOf(option));
                        out.writeUTF(speciality);
                        var doctors = (List<Doctor>) objectIn.readObject();
                        System.out.println(doctors);
                        message = in.readUTF();
                        break;
                    case 4:
                        System.out.println("== Update Diagnosis ==");
                        System.out.println("Enter patient ID: ");
                        String patientId = scanner.nextLine();
                        System.out.println("Enter doctor ID: ");
                        String doctorId = scanner.nextLine();
                        System.out.println("Enter diagnosis: ");
                        String diagnosis = scanner.nextLine();
                        out.writeUTF(String.valueOf(option));
                        out.writeUTF(patientId);
                        out.writeUTF(doctorId);
                        out.writeUTF(diagnosis);
                        message = in.readUTF();
                        break;
                    default: {
                        System.out.println("Invalid option");
                        break;
                    }
                }

                System.out.println(message);
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
