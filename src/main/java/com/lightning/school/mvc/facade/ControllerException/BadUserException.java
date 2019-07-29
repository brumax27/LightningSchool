package com.lightning.school.mvc.facade.ControllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadUserException extends RuntimeException {

    public BadUserException() {
        super("User finded isn't Student");
    }
}
