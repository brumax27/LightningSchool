package com.lightning.school.mvc.api.in.exercie;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Graph {
    private String outG;
    private List<String> inputG;
    private Map<String, Operator> body;
}
