package com.lightning.school.mvc.model;

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
@Table(name = "USER")
public class Class implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_CLASS")
    private Integer classId;
    @Column(name = "LIBELLE_CLASS")
    private String labelClass;
    @Column(name = "YEAR_PROMOTION")
    private Integer promotionYear;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<User> users;
}
