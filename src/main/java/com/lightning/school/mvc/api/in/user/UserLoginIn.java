package com.lightning.school.mvc.api.in.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@ApiModel(description = "Model de donnée pour Sign In")
public class UserLoginIn {

    @Email
    @ApiModelProperty(notes = "mail utilisateur")
    private String mail;
    @ApiModelProperty(notes = "password utilisateur")
    private String password;

}
