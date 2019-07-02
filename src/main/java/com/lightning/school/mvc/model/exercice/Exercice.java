package com.lightning.school.mvc.model.exercice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightning.school.mvc.api.in.exercie.ExerciceIn;
import com.lightning.school.mvc.model.graph.component.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Exercice")
@Table(name = "EXERCICE")
public class Exercice implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Exercice.class);

    @Id
    @GeneratedValue
    @Column(name = "ID_EXERCICE")
    private Integer exerciceId;
    @Column(name = "ID_T_EXERCICE")
    private Integer exerciceTypeId;
    @Column(name = "LIBELLE_EXERCICE")
    private String exerciceLabel;
    private String exoGraph;
    private String exoGraphCorriger;
    @Column(name = "COEFFICIENT")
    private Float coeficient;

    public ExerciceTypeEnum getExerciceType(){
        return ExerciceTypeEnum.retrieveExercieTypeEnumByValue(this.exerciceTypeId);
    }

    public Exercice(ExerciceIn in) {
        this.exerciceTypeId = ExerciceTypeEnum.retrieveExercieTypeEnumByValue(in.getExerciceTypeEnum());
        this.exerciceLabel = in.getExerciceLabel();
        this.coeficient = in.getCoeficient();
        String graph = null;
        try {
            graph = new ObjectMapper().writeValueAsString(in.getGraph());
        } catch (JsonProcessingException e) {
            LOGGER.warn("{}", e.getMessage());
        }
        if (ExerciceTypeEnum.TEACHER.getValue().equals(this.exerciceTypeId))
            this.exoGraphCorriger = graph;
        else
            this.exoGraph = graph;

    }

    public Graph getExoGraph() {
        try {
            return new ObjectMapper().readValue(this.exoGraph, Graph.class);
        } catch (IOException e) {
            LOGGER.error("{}", e.getMessage());
            return new Graph();
        }
    }

    public void setExoGraph(Graph exoGraph) {
        try {
            this.exoGraph = new ObjectMapper().writeValueAsString(exoGraph);
        } catch (JsonProcessingException e) {
            LOGGER.error("{}", e.getMessage());
        }
    }

    public Graph getExoGraphCorriger() {
        try {
            return new ObjectMapper().readValue(this.exoGraphCorriger, Graph.class);
        } catch (IOException e) {
            LOGGER.error("{}", e.getMessage());
            return new Graph();
        }
    }

    public void setExoGraphCorriger(Graph exoGraphCorriger) {
        try {
            this.exoGraphCorriger = new ObjectMapper().writeValueAsString(exoGraphCorriger);
        } catch (JsonProcessingException e) {
            LOGGER.error("{}", e.getMessage());
        }
    }
}
