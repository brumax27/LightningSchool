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
@NoArgsConstructor
@AllArgsConstructor
@Validated
@ApiModel(description = "Model pour mettre à jour un utilisateur")
public class UserUpdateIn {

    @Email
    @ApiModelProperty(notes = "mail utilisateur")
    private String mail;
    @ApiModelProperty(notes = "password utilisateur")
    private String password;
    @ApiModelProperty(notes = "prénom de l'utilisateur")
    private String name;
    @ApiModelProperty(notes = "nom de l'utilisateur")
    private String surname;
    @ApiModelProperty(notes = "url photo de l'utilisateur")
    private String userPhoto;
    @ApiModelProperty(notes = "type d'utilisateur")
    private UserTypeEnum userType;

}
