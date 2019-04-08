package com.lightning.school.mvc.model;

import com.lightning.school.mvc.model.exercice.Exercice;
import com.lightning.school.mvc.model.user.User;
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DEADLINE")
    private LocalDateTime deadline;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Class> classes;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    List<Page> pages;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    List<Exercice> exercices;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    List<Media> medias;
}
