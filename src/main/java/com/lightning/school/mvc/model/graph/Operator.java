package com.lightning.school.mvc.model.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator implements Node {

    private Node left;
    private Node right;
    private OperatorType operator;

    @Override
    public boolean getValue() {
        switch (operator){
            case AND:
                return left.getValue() && right.getValue();
            case OR:
                return left.getValue() || right.getValue();
            case NOT:
                return !left.getValue();
        }
        return false;
    }

}
