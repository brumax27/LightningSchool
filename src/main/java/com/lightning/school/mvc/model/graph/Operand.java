package com.lightning.school.mvc.model.graph;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Operand implements Node {

    @Setter
    private boolean value;

    @Override
    public boolean getValue() {
        return value;
    }


}