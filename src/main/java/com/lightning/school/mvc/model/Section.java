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
@Entity(name = "Section")
@Table(name = "SECTION")
public class Section implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_SECTION")
    private Integer sectionId;
    @Column(name = "LIBELLE_SECTION")
    private String sectionLabel;
    @Column(name = "YEAR_PROMOTION")
    private Integer promotionYear;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_users", updatable = false, insertable = false)
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_groups", updatable = false, insertable = false)
    private List<Cours> groups;
}
