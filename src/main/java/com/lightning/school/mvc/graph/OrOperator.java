package com.lightning.school.mvc.graph;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrOperator implements BooleanOperator {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    private final String label = "OR";

    @Override
    public final boolean operation(boolean a, boolean b){
        return a || b;
    }
}
