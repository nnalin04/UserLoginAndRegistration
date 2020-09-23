package com.bridgelabz.userloginregistration.exception;

public class UserLoginException extends Exception {

    public enum ExceptionType{
        EMAIL_ID_NOT_PRESENT, LIMIT_EXCEEDED;
    }

    ExceptionType type;

    public UserLoginException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
}
