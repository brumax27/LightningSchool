package com.lightning.school.mvc.model.graph.component;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@Entity(name = "InnerComposant")
@Table(name = "InnerComposant")
public class InnerComposant implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="output", columnDefinition="INT(1)")
    private Boolean output;
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="inner_composant_inputs", joinColumns=@JoinColumn(name="inner_composant_id"))
    private Map<String, Boolean> inputs;
    @Enumerated(EnumType.STRING)
    private OperatorType operator;

}
