package org.alexv.wanderlustapi.api.exception;

public class UserEmailAlreadyTaken extends RuntimeException {
    public UserEmailAlreadyTaken(String message) {
        super(message);
    }
}
