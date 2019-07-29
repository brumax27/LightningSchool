package com.lightning.school.mvc.facade.ControllerException.verrify.exo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingOperandException extends RuntimeException {

    public MissingOperandException() {
        super("Missing operand");
    }
}
