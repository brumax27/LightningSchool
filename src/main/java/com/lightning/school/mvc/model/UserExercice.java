package com.lightning.school.mvc.model;

import com.lightning.school.mvc.model.exercice.Exercice;
import com.lightning.school.mvc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserExercice")
@Table(name = "USER_EXERCICE")
public class UserExercice implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private Exercice exercice;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(name = "PATH_RENDU")
    private String pathExerciceRender;
    @Column(name = "MARK")
    private Float mark;
    @Column
    private Integer trying = 0;
    @Column(name = "validate_at")
    private LocalDateTime validateAt;
}
