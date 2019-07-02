package com.lightning.school.mvc.model.graph.component;

import lombok.Data;

import java.util.List;

@Data
public class Graph {

    private Output outG;
    private List<Input> inputsG;
    private List<Operator> body;
}
