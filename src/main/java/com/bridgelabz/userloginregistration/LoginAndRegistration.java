package com.bridgelabz.userloginregistration;

import com.bridgelabz.userloginregistration.pojo.User;
import com.bridgelabz.userloginregistration.exception.UserLoginException;
import com.bridgelabz.userloginregistration.service.UserService;
import com.bridgelabz.userloginregistration.utility.RegisterUser;
import java.sql.SQLException;

public class LoginAndRegistration {

    public static void main(String[] args) throws SQLException, UserLoginException {
        User userDetail = new User();
        UserService userService = new UserService();
        System.out.println("Want to register : 1");
        System.out.println("Want to perform other function : 2");
        int choice = RegisterUser.takingIntInput();
        switch(choice) {
            case 1:
                InputFromUser(userDetail);
                userService.registerUser(userDetail);
                break;
            case 2:
                System.out.println("LogIn into the system to perform other task");
                System.out.println("Enter the Login detail");
                System.out.print("Enter email Id :- ");
                String email = RegisterUser.takingStringInput();
                System.out.println("Enter password :- ");
                String password = RegisterUser.takingStringInput();

                User user = userService.loginUser(email, password);
                System.out.println("want to edit info : 1");
                System.out.println("want to delete profile : 2");
                int choice1 = RegisterUser.takingIntInput();

                switch(choice1) {
                    case 1:
                        InputFromUser(user);
                        userService.updateUser(user);
                        break;
                    case 2:
                        userService.deleteUser(user.email);
                        break;
                    default:
                        System.out.println("Incorrect input");
                }
                break;
            default:
                System.out.println("Incorrect input");
        }
        System.out.println(userDetail);
    }

    private static void InputFromUser(User userDetail) {
        System.out.print("First Name :- ");
        userDetail.firstName = RegisterUser.takingStringInput();

        System.out.print("Last Name :- ");
        userDetail.lastName = RegisterUser.takingStringInput();

        System.out.print("Email Id :- ");
        userDetail.email = RegisterUser.takingStringInput();

        System.out.print("Password :- ");
        userDetail.password = RegisterUser.takingStringInput();

        System.out.print("phoneNo. :- ");
        userDetail.phoneNo = RegisterUser.takingStringInput();
    }
}
