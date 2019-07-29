package com.lightning.school.mvc.facade.ControllerException.verrify.exo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NpiEmptyEception extends RuntimeException {
    public NpiEmptyEception() {
        super("String npi is empty");
    }
}
