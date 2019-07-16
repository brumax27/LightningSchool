package com.lightning.school.mvc.facade.ControllerException;

public class NoDataException extends RuntimeException {

    public NoDataException(Integer exoId) {
        super("No ressource of id => " + exoId);
    }
}
