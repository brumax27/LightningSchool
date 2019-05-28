//package com.lightning.school.mvc.model;
//
//import com.lightning.school.mvc.model.exercice.Exercice;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "GroupExercice")
//@Table(name = "GROUP_EXERCICE")
//public class GroupExercice implements Serializable {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ID_GROUP_EXERCICE")
//    private Integer groupExerciceId;
//    @OneToOne
//    private Group group;
//    @OneToOne
//    private Exercice exercice;
//    @Column(name = "PATH_RENDU")
//    private String pathExerciceRender;
//    @Column(name = "MARK")
//    private Float mark;
//
//}
