package com.lightning.school.mvc.facade.ControllerException.verrify.exo;

public class MissingOperandException extends RuntimeException {

    public MissingOperandException() {
        super("Missing operand");
    }
}
