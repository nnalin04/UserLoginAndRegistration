package com.bridgelabz.userloginregistration.utility;

import java.util.Scanner;

public class RegisterUser {

    private static final Scanner scan = new Scanner(System.in);

    public static String takingStringInput(){
        scan.nextLine();
        return scan.nextLine();
    }

    public static int takingIntInput() {
        return scan.nextByte();
    }
}
