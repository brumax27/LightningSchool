package com.lightning.school.mvc.api.in.exercie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciceIn {

    private Integer exerciceId;
    private String exerciceLabel;
    private Float coeficient;
    private Integer coursId;
    private Graph graph;

}
