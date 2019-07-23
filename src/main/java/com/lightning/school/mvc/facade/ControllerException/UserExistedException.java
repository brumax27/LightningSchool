package com.lightning.school.mvc.facade.ControllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistedException extends RuntimeException {

    public UserExistedException(String mail) {
        super("this mail exist => " + mail);
    }
}
