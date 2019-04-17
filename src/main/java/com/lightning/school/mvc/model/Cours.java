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
@Entity
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

    @ManyToMany
    private List<Section> sections;

    @ManyToMany
    List<Page> pages;

    @ManyToMany
    List<Exercice> exercices;

    @ManyToMany
    List<Media> medias;
}
