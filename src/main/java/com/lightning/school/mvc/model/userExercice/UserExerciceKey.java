package com.lightning.school.mvc.model.userExercice;

import com.lightning.school.mvc.model.exercice.Exercice;
import com.lightning.school.mvc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserExerciceKey implements Serializable {

    @OneToOne
    private Exercice exercice;
    @OneToOne
    private User User;
}
