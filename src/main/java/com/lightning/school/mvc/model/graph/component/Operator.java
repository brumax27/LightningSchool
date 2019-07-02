package com.lightning.school.mvc.model.graph.component;

import lombok.Data;

import java.util.List;

@Data
public class Operator {

    private Output out;
    private List<Input> inputs;
    private OperatorType operatorType;
}
