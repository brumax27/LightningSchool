package com.lightning.school.mvc.facade.ControllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CrudException extends RuntimeException {

    public CrudException() {
        super("Error data in payload");
    }
}
