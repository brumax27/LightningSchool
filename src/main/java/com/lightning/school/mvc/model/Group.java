//package com.lightning.school.mvc.model;
//
//import com.lightning.school.mvc.model.user.User;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "Group")
//@Table(name = "GROUP")
//public class Group implements Serializable {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ID_GROUP")
//    private Integer groupId;
//    @Column(name = "LIBELLE_GROUP")
//    private String groupLabel;
//
//    @ManyToMany
//    List<User> users;
//
//}
