package com.lightning.school.mvc.facade.ControllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoDataException extends RuntimeException {

    public NoDataException(Integer exoId) {
        super("No ressource of id => " + exoId);
    }
}
