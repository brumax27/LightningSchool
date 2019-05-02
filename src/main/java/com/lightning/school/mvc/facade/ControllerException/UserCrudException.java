package com.lightning.school.mvc.facade.ControllerException;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCrudException extends RuntimeException {

    @Getter
    private final int errorCode = 1001;

    public UserCrudException() {
        super("Error data in payload of User");
    }
}
