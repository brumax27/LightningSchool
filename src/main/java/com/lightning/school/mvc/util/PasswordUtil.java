package com.lightning.school.mvc.util;

public final class PasswordUtil {

    private PasswordUtil() {
    }

    public static boolean passwordIsValid(String password){
        boolean ok = password.length() >= 8;
        ok &= password.length() <= 32;
        ok &= password.matches("[a-zA-Z0-9]*");
        return ok;
    }
}
