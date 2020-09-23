package com.bridgelabz.userloginregistration.service;

import java.util.regex.Pattern;

public class UserRegistration {

    public enum ValidatorPat {
        NAME(NAME_PATTERN), EMAIL(EMAIL_PATTERN), MOBILE(MOBILE_NUMBER_PATTERN), PASSWORD(PASSWORD_PATTERN);

        private final String validPattern;

        ValidatorPat(String validPattern) {
            this.validPattern = validPattern;
        }
    }

    private static final String NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}([ ][A-Z]{1}[a-z]{2,})*";
    private static final String EMAIL_PATTERN = "^[0-9a-zA-Z]+([_-][0-9a-zA-Z]+)*@[0-9a-zA-Z]+.[0-9a-zA-Z]{2,4}([.][a-zA-Z]{2})*$";
    private static final String MOBILE_NUMBER_PATTERN = "^(([1-9]{1}([-][1-9]{1}[0-9]{1,2}||[0-9]{1,3})) {1})*[1-9]{1}[0-9]{9}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z].*)(?=.*[0-9].*)(?=.*[!@#$%^&*+].*)[0-9a-zA-Z!@#$%^&*+]{8,}$";

    public static boolean validateInput(String input, ValidatorPat inputType) {
        String validPattern = inputType.validPattern;
        Pattern pattern = Pattern.compile(validPattern);
        return pattern.matcher(input).matches();
    }
}
