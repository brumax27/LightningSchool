package com.lightning.school.mvc.api.out;

import com.lightning.school.mvc.api.out.error.ErrorDto;
import com.lightning.school.mvc.model.graph.component.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciceVerifyOut {

    private ErrorDto error;
    private Graph graph;
    private Boolean verified;
    private Boolean result;


}
