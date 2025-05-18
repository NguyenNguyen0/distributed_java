import model.Doctor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            System.out.println("5. Delete Doctor");
            System.out.println("6. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 6) {
                break;
            }

            try {
                Socket socket = new Socket("NGUYEN", 2975);
                // Create output stream first, then input stream
                ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());

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

                        objectOut.writeObject(String.valueOf(option));
                        objectOut.writeObject(doctor);
                        message = (String) objectIn.readObject();
                        break;
                    case 2:
                        System.out.println("== No Of Doctors By Speciality ==");
                        System.out.println("Enter department name: ");
                        String departmentName = scanner.nextLine();

                        objectOut.writeObject(String.valueOf(option));
                        objectOut.writeObject(departmentName);
                        Map<String, Long> doctorBySpeciality = (Map<String, Long>) objectIn.readObject();
                        System.out.println(doctorBySpeciality);
                        message = (String) objectIn.readObject();
                        break;
                    case 3:
                        System.out.println("== List Doctors By Speciality ==");
                        System.out.println("Enter speciality: ");
                        String speciality = scanner.nextLine();
                        objectOut.writeObject(String.valueOf(option));
                        objectOut.writeObject(speciality);
                        var doctors = (List<Doctor>) objectIn.readObject();
                        System.out.println(doctors);
                        message = (String) objectIn.readObject();
                        break;
                    case 4:
                        System.out.println("== Update Diagnosis ==");
                        System.out.println("Enter patient ID: ");
                        String patientId = scanner.nextLine();
                        System.out.println("Enter doctor ID: ");
                        String doctorId = scanner.nextLine();
                        System.out.println("Enter diagnosis: ");
                        String diagnosis = scanner.nextLine();
                        objectOut.writeObject(String.valueOf(option));
                        objectOut.writeObject(patientId);
                        objectOut.writeObject(doctorId);
                        objectOut.writeObject(diagnosis);
                        message = (String) objectIn.readObject();
                        break;
                    case 5:
                        System.out.println("=== Delete Doctor ===");
                        System.out.println("Enter doctor ID: ");
                        String doctorIdToDelete = scanner.nextLine();
                        objectOut.writeObject(String.valueOf(option));
                        objectOut.writeObject(doctorIdToDelete);
                        message = (String) objectIn.readObject();
                        break;
                    default: {
                        System.out.println("Invalid option");
                        break;
                    }
                }

                System.out.println(message);

                // Close resources
                objectOut.close();
                objectIn.close();
                socket.close();

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