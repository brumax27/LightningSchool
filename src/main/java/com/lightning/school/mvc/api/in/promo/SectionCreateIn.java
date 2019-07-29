package com.lightning.school.mvc.api.in.promo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Model pour creer une promotions")
public class SectionCreateIn {

    @ApiModelProperty(notes = "Label de la promotions")
    private String sectionLabel;
    @ApiModelProperty(notes = "Année de la promotions")
    private Integer promotionYear;
    @ApiModelProperty(notes = "Id de l'enseignant en charge")
    private Integer teacherId;


}
