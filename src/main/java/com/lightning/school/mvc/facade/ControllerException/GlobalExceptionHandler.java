package com.lightning.school.mvc.facade.ControllerException;


import com.lightning.school.mvc.api.out.error.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.badRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserCrudException.class)
    public ResponseEntity handleException(UserCrudException ex){
        return badRequest().body(new ErrorDto(ex.getMessage(), ex.getErrorCode()));
    }

    @ExceptionHandler(MailCustomException.class)
    public ResponseEntity handleException(MailCustomException ex){
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleException(UserNotFoundException ex){
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity handleException(PasswordInvalidException ex){
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(UserDoesExistException.class)
    public ResponseEntity handleException(UserDoesExistException ex){
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

}