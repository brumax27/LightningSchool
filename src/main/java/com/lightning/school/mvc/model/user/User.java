package com.lightning.school.mvc.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lightning.school.mvc.model.Group;
import com.lightning.school.mvc.model.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer userId;
    @Email
    @Column(name = "MAIL", nullable = false)
    private String mail;
    @Column(name = "ID_T_USER")
    private Integer typeUserId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;
    @Column(name = "PHOTO_PATH")
    private String userPhoto;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_section", updatable = false, insertable = false)
    @JsonIgnore
    private List<Section> sections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_group", updatable = false, insertable = false)
    @JsonIgnore
    List<Group> groups;

    public User(String email, String password, Integer typeUserId){
        this.password = password;
        this.mail = email;
        this.typeUserId = typeUserId;
    }

    @JsonIgnore
    public UserTypeEnum getUserType(){
        return UserTypeEnum.retrieveTypeUserByValue(this.typeUserId);
    }

}
