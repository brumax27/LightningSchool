package com.lightning.school.mvc.graph;

import com.lightning.school.mvc.graph.exception.MethodBooleanExecption;
import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotOperator implements BooleanOperator{

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    private final String label = "NOT";

    /**
     * no usable method because not implemented for notOperator
     */
    @Deprecated
    @Override
    public final boolean operation(boolean a, boolean b){
        throw new MethodBooleanExecption("no usable method, use method 'boolean operation(boolean a)' !!!");
    }

    public final boolean operation(boolean a){
        return !a;
    }
}
