package com.lightning.school.mvc.api.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateIn {

    @Email
    private String mail;
    private String password;

    public boolean passwordIsValid(){
        boolean ok = this.password.length() >= 8;
        ok &= this.password.matches("[a-zA-Z0-9]*");
        return ok;
    }

}
