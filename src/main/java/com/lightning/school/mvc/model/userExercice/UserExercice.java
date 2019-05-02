package com.lightning.school.mvc.model.userExercice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserExercice")
@Table(name = "USER_EXERCICE")
public class UserExercice implements Serializable {

    @EmbeddedId
    private UserExerciceKey userExerciceKey;
    @Column(name = "PATH_RENDU")
    private String pathExerciceRender;
    @Column(name = "MARK")
    private Float mark;

}
