package com.lightning.school.mvc.facade.ControllerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MailCustomException extends RuntimeException {

    public MailCustomException() {
        super("Mail sender or Mail process error");
    }
}
