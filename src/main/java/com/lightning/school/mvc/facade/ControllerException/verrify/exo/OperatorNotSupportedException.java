package com.lightning.school.mvc.facade.ControllerException.verrify.exo;

public class OperatorNotSupportedException extends RuntimeException {

    public OperatorNotSupportedException(Character c) {
        super("Operator " + c + " not Supported");
    }
}
