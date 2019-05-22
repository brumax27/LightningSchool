package com.lightning.school.mvc.model;

import com.lightning.school.mvc.model.exercice.Exercice;
import com.lightning.school.mvc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserExercice")
@Table(name = "USER_EXERCICE")
public class UserExercice implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private Exercice exercice;
    @OneToOne
    private User User;
    @Column(name = "PATH_RENDU")
    private String pathExerciceRender;
    @Column(name = "MARK")
    private Float mark;

}
