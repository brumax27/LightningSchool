package com.lightning.school.mvc.api.in.user;

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
@ApiModel(description = "Model de donnée pour mot de passe oublier")
public class UserRecoveryIn {

    @Email
    @ApiModelProperty(notes = "mail utilisateur")
    private String mail;
}
