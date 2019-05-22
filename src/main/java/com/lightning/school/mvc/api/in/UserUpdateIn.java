package com.lightning.school.mvc.api.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateIn {

    @Email
    private String mail;
    private String password;
    private String name;
    private String surname;
    private String userPhoto;
    private Integer userType = 0;

}
