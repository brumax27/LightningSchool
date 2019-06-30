package com.lightning.school.mvc.model.graph.component;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
public class VerityMatrixRow implements Serializable {

    private Boolean in1;
    private Boolean in2;
    private Boolean out;

}
