package com.lightning.school.mvc.model.graph.component;

import lombok.Data;

import java.util.List;

@Data
public class Operator implements Node {

    private Output out;
    private List<Input> inputs;
    private OperatorType operatorType;
}
