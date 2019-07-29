package com.lightning.school.mvc.api.in.promo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model pour editer une promotions")
public class SectionUpdateIn {

    @ApiModelProperty(notes = "Id de la promotion")
    private Integer id;
    @ApiModelProperty(notes = "Label de la promotions")
    private String sectionLabel;
    @ApiModelProperty(notes = "Ids des etudiants à associer")
    private List<Integer> userIds;
    @ApiModelProperty(notes = "Ids des cours à associer")
    private List<Integer> coursIds;


}
