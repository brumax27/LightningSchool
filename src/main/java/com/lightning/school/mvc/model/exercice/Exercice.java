package com.lightning.school.mvc.model.exercice;

import com.lightning.school.mvc.api.in.ExerciceIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Exercice")
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
    private String npi;
    @Column(name = "COEFFICIENT")
    private Float coeficient;

    public ExerciceTypeEnum getExerciceType(){
        return ExerciceTypeEnum.retrieveExercieTypeEnumByValue(this.exerciceTypeId);
    }

    public Exercice(ExerciceIn in) {
        this.exerciceTypeId = ExerciceTypeEnum.TEACHER.getValue();
        this.exerciceLabel = in.getExerciceLabel();
        this.coeficient = in.getCoeficient();
        this.npi = in.getNpi();
    }

    public Exercice(Exercice teacher, String npiStudent) {
        this.exerciceTypeId = ExerciceTypeEnum.STUDENT.getValue();
        this.exerciceLabel = teacher.getExerciceLabel();
        this.coeficient = teacher.getCoeficient();
        this.npi = npiStudent;
    }
}
