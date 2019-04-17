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
@Table(name = "SECTION")
public class Section implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_SECTION", nullable = false)
    private Integer sectionId;
    @Column(name = "LIBELLE_SECTION")
    private String sectionLabel;
    @Column(name = "YEAR_PROMOTION")
    private Integer promotionYear;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Cours> groups;
}
