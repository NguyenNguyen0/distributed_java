import service.IGroupService;
import service.IUserService;
import service.impl.GroupService;
import service.impl.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        IUserService userService = (IUserService) context.lookup("rmi://NGUYEN:2975/UserService");
        IGroupService groupService = (IGroupService) context.lookup("rmi://NGUYEN:2975/GroupService");

        while (true) {
            System.out.println("========MENU========");
            System.out.println("0. Exit");
            System.out.println("1. List all users");
            System.out.println("2. List all groups");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    userService.getAll().forEach(System.out::println);
                    break;
                case 2:
                    groupService.getAll().forEach(System.out::println);
                    break;
                case 0:
                    return;
            }
        }
    }
}
