package com.lightning.school.mvc.model;

import com.lightning.school.mvc.model.user.User;
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
@Table(name = "CLASS")
public class Class implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_CLASS", nullable = false)
    private Integer classId;
    @Column(name = "LIBELLE_CLASS")
    private String labelClass;
    @Column(name = "YEAR_PROMOTION")
    private Integer promotionYear;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<User> users;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Cours> groups;
}
