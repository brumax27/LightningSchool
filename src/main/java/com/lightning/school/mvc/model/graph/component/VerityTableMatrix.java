package com.lightning.school.mvc.model.graph.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class VerityTableMatrix implements Serializable {

    private List<VerityMatrixRow> row;

}
