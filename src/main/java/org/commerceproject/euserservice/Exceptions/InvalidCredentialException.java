package org.commerceproject.euserservice.Exceptions;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message);
    }
}
