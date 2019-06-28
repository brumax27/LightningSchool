package com.lightning.school.mvc.model;

import com.lightning.school.mvc.api.in.promo.SectionCreateIn;
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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cours> cours;

    public Section(SectionCreateIn in) {
        this.sectionLabel = in.getSectionLabel();
        this.promotionYear = in.getPromotionYear();
    }
}
