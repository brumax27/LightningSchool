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
    @Column(name = "link_cours")
    private String linkCours;

    @OneToMany(cascade = CascadeType.ALL)
    List<Exercice> exercices;

    @OneToMany(cascade = CascadeType.ALL)
    List<Media> medias;

    public Cours(String coursLabel, LocalDateTime deadline, String linkCours, List<Media> medias) {
        this.coursLabel = coursLabel;
        this.deadline = deadline;
        this.linkCours = linkCours;
        this.medias = medias;
    }
}
