package com.lightning.school.mvc.model.exercice;

import com.lightning.school.mvc.model.Cours;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXERCICE")
public class Exercice implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_EXERCICE")
    private Integer exerciceId;
    @Column(name = "ID_T_EXERCICE")
    private Integer exerciceTypeId;
    @Column(name = "LIBELLE_EXERCICE")
    private String exerciceLabel;
    @Column(name = "PATH")
    private String exercicePath;
    @Column(name = "COEFFICIENT")
    private Float coeficient;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    List<Cours> cours;

    public ExerciceTypeEnum getExerciceType(){
        return ExerciceTypeEnum.retrieveExercieTypeEnumByValue(this.exerciceTypeId);
    }

}
