package com.lightning.school.mvc.facade.ControllerException.verrify.exo;

public class NpiEmptyEception extends RuntimeException {
    public NpiEmptyEception() {
        super("String npi is empty");
    }
}
