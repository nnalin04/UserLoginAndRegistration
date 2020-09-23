package com.bridgelabz.userloginregistration.service;

import com.bridgelabz.userloginregistration.exception.UserLoginException;
import com.bridgelabz.userloginregistration.pojo.User;
import com.bridgelabz.userloginregistration.repo.UserRepo;
import com.bridgelabz.userloginregistration.utility.RegisterUser;
import java.sql.SQLException;

public class UserService {

    UserRepo userRepo = new UserRepo();;

    public UserService() {
    }

    public void registerUser(User user) throws SQLException {

        user.firstName = checkFieldValidation(UserRegistration.ValidatorPat.NAME,
                user.firstName, "first name");
        user.lastName = checkFieldValidation(UserRegistration.ValidatorPat.NAME,
                user.lastName, "last name");
        user.email = checkFieldValidation(UserRegistration.ValidatorPat.EMAIL,
                user.email, "email Id");
        user.password = checkFieldValidation(UserRegistration.ValidatorPat.PASSWORD,
                user.password, "password");
        user.phoneNo = checkFieldValidation(UserRegistration.ValidatorPat.MOBILE,
                user.phoneNo, "phone no.");
        userRepo.saveUserInDatabase(user);
    }

    public User loginUser(String email, String password) throws UserLoginException, SQLException {
        User user = userRepo.findByEmailId(email);
        int count = 0;
        if (user == null)
            throw new UserLoginException("email not present",
                                        UserLoginException.ExceptionType.EMAIL_ID_NOT_PRESENT);
        while(!user.password.equals(password) || count < 2){
            System.out.println("Enter the correct password");
            password = RegisterUser.takingStringInput();
            count++;
        }
        if (count == 3)
            throw new UserLoginException("exceeded the input limit",
                                        UserLoginException.ExceptionType.LIMIT_EXCEEDED);
        return user;
    }

    public void updateUser(User user) throws SQLException {
        System.out.println("What do you want to up date");
        System.out.println("1 - email");
        System.out.println("2 - password");
        System.out.println("3 - phoneNo");
        int choice = RegisterUser.takingIntInput();
        switch(choice) {
            case 1:
                System.out.println("Enter the new email");
                String newEmail = RegisterUser.takingStringInput();

                newEmail = checkFieldValidation(UserRegistration.ValidatorPat.EMAIL,
                        newEmail, "email Id");
                userRepo.updateUser(user, newEmail, "email");
                break;
            case 2:
                System.out.println("Enter the new password");
                String newPassword = RegisterUser.takingStringInput();

                newPassword = checkFieldValidation(UserRegistration.ValidatorPat.EMAIL,
                        newPassword, "email Id");
                userRepo.updateUser(user, newPassword, "password");
                break;
            case 3:
                System.out.println("Enter the new email");
                String newPhoneNo = RegisterUser.takingStringInput();

                newPhoneNo = checkFieldValidation(UserRegistration.ValidatorPat.EMAIL,
                        newPhoneNo, "email Id");
                userRepo.updateUser(user, newPhoneNo, "phoneNo");
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public void deleteUser(String email) throws SQLException {
        userRepo.deleteByEmailId(email);
    }

    private String checkFieldValidation(UserRegistration.ValidatorPat pattern, String input, String type)
            throws SQLException {

        while (userRepo.findByEmailId(input) != null){
            System.out.println(type+" already present please enter different "+type);
            input = RegisterUser.takingStringInput();
        }

        while(!UserRegistration.validateInput(input, pattern)){
            System.out.println("Invalid "+type+" please re-enter");
            input = RegisterUser.takingStringInput();
        }
        return input;
    }
}
