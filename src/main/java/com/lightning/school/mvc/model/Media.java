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
@Table(name = "MEDIA")
public class Media implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_MEDIA")
    private Integer mediaId;
    @Column(name = "LIBELLE")
    private String label;
    @Column(name = "PATH")
    private String path;

    @ManyToMany
    List<Cours> cours;
}
