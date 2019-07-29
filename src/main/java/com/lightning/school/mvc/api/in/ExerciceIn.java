package com.lightning.school.mvc.api.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model pour creer un exercice")
public class ExerciceIn {

    @ApiModelProperty(notes = "Id de l'exercice a éditer")
    private Integer exerciceId;
    @ApiModelProperty(notes = "Label de l'exercice")
    private String exerciceLabel;
    @ApiModelProperty(notes = "Coéficient de l'exercice")
    private Float coeficient;
    @ApiModelProperty(notes = "Id du cours auquel l'exercice doit êtres associé")
    private Integer coursId;
    @ApiModelProperty(notes = "Représentation RPN ou NPI du graph de l'exercice")
    private String npi;

}
