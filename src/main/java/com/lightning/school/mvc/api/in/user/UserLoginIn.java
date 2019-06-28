package com.lightning.school.mvc.api.in.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginIn {

    @Email
    private String mail;
    private String password;

}
