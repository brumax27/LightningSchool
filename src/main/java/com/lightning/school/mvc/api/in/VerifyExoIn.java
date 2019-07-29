package com.lightning.school.mvc.api.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model pour verifier un exercice")
public class VerifyExoIn {

    @ApiModelProperty(notes = "Représentation RPN ou NPI du graph de l'exercice")
    private String npi;
    @ApiModelProperty(notes = "id de récuperation de la liaison entre le user et l'exercice")
    private Integer studentExerciceId;
    @ApiModelProperty(notes = "Id utilisateur de l'etudiant")
    private Integer studentId;
    @ApiModelProperty(notes = "Id de l'exercice de l'utilisateur enseignant")
    private Integer exerciceTeacherId;

}
