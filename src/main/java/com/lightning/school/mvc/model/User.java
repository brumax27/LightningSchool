package com.lightning.school.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.TypeElement;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER")
    private Integer userId;
    @Column(name = "ID_T_USER")
    private Integer typeUserId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Id
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHOTO_PATH")
    private String userPhoto;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Class> classes;

    public TypeUserEnum getUserType(){
        return TypeUserEnum.retrieveTypeUserByValue(this.typeUserId);
    }

}
