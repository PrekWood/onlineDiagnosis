package com.example.onlineDiagnosis.User.exceptions;

public class EmailAlreadyBeingUsedUserException extends Exception{
    public EmailAlreadyBeingUsedUserException(String errorMessage) {
        super(errorMessage);
    }
}
