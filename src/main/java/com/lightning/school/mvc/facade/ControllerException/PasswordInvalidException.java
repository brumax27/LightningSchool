package com.lightning.school.mvc.facade.ControllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException() {
        super("password not valid (number, minuscule, majuscule, >8 , <32)");
    }
}
