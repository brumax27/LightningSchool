package com.lightning.school.mvc.facade.ControllerException;


import com.lightning.school.mvc.api.out.error.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.badRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CrudException.class)
    public ResponseEntity handleException(CrudException ex){
        return badRequest().body(new ErrorDto(ex.getMessage()));
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

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handleException(AuthException ex){
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleException(MissingServletRequestParameterException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity handleException(UserExistedException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity handleException(NoDataException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(BadUserException.class)
    public ResponseEntity handleException(BadUserException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage()));
    }

}