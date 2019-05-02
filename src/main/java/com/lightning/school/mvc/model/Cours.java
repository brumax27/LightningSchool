package com.lightning.school.mvc.model;

import com.lightning.school.mvc.model.exercice.Exercice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cours")
@Table(name = "COURS")
public class Cours implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_COURS")
    private Integer coursId;
    @Column(name = "LIBELLE_COURS")
    private String coursLabel;
    @Column(name = "DEADLINE")
    private LocalDateTime deadline;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_sections", updatable = false, insertable = false)
    private List<Section> sections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_pages", updatable = false, insertable = false)
    List<Page> pages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_exercices", updatable = false, insertable = false)
    List<Exercice> exercices;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_medias", updatable = false, insertable = false)
    List<Media> medias;
}
