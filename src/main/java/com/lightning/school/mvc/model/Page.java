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
@Table(name = "PAGE")
public class Page implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_PAGE", nullable = false)
    private Integer pageId;
    @Column(name = "CONTENU_PAGE")
    private String pageContent;

    @ManyToMany
    List<Cours> cours;

}
