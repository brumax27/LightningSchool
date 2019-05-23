package com.lightning.school.mvc.api.in;

import com.lightning.school.mvc.model.user.UserTypeEnum;
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
@ApiModel(description = "Model pour creer un utilisateur")
public class UserCreateIn {

    @Email
    @ApiModelProperty(notes = "mail utilisateur")
    private String mail;
    @ApiModelProperty(notes = "password utilisateur")
    private String password;
    @ApiModelProperty(notes = "type de user si non specifier le user est un student")
    private UserTypeEnum typeUser = UserTypeEnum.STUDENT;

}
