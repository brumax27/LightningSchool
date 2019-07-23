package com.lightning.school.mvc.model.graph.component;

import lombok.Data;

import java.io.Serializable;

@Data
public class Output implements Serializable, Io, Node {

    private String name;
    private boolean state;
}
