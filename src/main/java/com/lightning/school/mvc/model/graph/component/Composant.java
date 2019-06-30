package com.lightning.school.mvc.model.graph.component;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Entity(name = "Composant")
@Table(name = "Composant")
public class Composant implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="output", columnDefinition="INT(1)")
    private Boolean output;
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="composant_inputs", joinColumns=@JoinColumn(name="composant_id"))
    private Map<String, Boolean> inputs;
    @OneToMany(cascade = CascadeType.ALL)
    private List<InnerComposant> innerComposants;
    @Column(name="resusable", columnDefinition="INT(1)")
    private Boolean reusable;
}
