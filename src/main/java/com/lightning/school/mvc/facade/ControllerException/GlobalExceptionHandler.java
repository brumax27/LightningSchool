package com.lightning.school.mvc.facade.ControllerException;


import com.lightning.school.mvc.api.out.error.ErrorCodeEnum;
import com.lightning.school.mvc.api.out.error.ErrorDto;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperandException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperatorException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.NpiEmptyEception;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.OperatorNotSupportedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.badRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CrudException.class)
    public ResponseEntity handleException(CrudException ex){
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.CRUD_EXCEPTION.getCode()));
    }

    @ExceptionHandler(MailCustomException.class)
    public ResponseEntity handleException(MailCustomException ex){
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.MAIL_CUSTOM_EXCEPTION.getCode()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleException(UserNotFoundException ex){
        return badRequest().body(new ErrorDto(ex.getMessage(),ErrorCodeEnum.USER_NOT_FOUNDED.getCode()));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity handleException(PasswordInvalidException ex){
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.PASSWORD_INVALID_EXCEPTION.getCode()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handleException(AuthException ex){
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.AUTH_EXCEPTION.getCode()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleException(MissingServletRequestParameterException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.SERVLET_EXCEPTION.getCode()));
    }

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity handleException(UserExistedException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.USER_EXISTED_EXCEPTION.getCode()));
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity handleException(NoDataException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.NO_DATA_EXCEPTION.getCode()));
    }

    @ExceptionHandler(BadUserException.class)
    public ResponseEntity handleException(BadUserException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.BAD_USER_EXCEPTION.getCode()));
    }

    // Exercice Exception


    @ExceptionHandler(MissingOperandException.class)
    public ResponseEntity handleException(MissingOperandException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.MISSING_OPERAND_EXCEPTION.getCode()));
    }

    @ExceptionHandler(MissingOperatorException.class)
    public ResponseEntity handleException(MissingOperatorException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.MISSING_OPERATOR_EXCEPTION.getCode()));
    }

    @ExceptionHandler(NpiEmptyEception.class)
    public ResponseEntity handleException(NpiEmptyEception ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.NPI_EMPTY_EXCEPTION.getCode()));
    }

    @ExceptionHandler(OperatorNotSupportedException.class)
    public ResponseEntity handleException(OperatorNotSupportedException ex) {
        return badRequest().body(new ErrorDto(ex.getMessage(), ErrorCodeEnum.OPERATOR_NOT_SUPORTED.getCode()));
    }
}