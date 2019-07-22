package com.lightning.school.mvc.facade.ControllerException;

public class BadUserException extends RuntimeException {

    public BadUserException() {
        super("User finded isn't Student");
    }
}
