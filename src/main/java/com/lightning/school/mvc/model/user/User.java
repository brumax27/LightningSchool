package com.lightning.school.mvc.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lightning.school.mvc.api.out.UserItem;
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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Section> sections;

//    @ManyToMany
//    List<Group> groups;

    public User(String email, String password, Integer typeUserId){
        this.password = password;
        this.mail = email;
        this.typeUserId = typeUserId;
    }

    public User(UserItem item){
        this.userId = item.getId();
        this.mail = item.getMail();
        this.typeUserId = UserTypeEnum.retrieveValueByUserType(item.getUserTypeEnum());
        this.name = item.getName();
        this.surname = item.getSurmane();
        this.userPhoto = item.getUserPhoto();
    }

    @JsonIgnore
    public UserTypeEnum getUserType(){
        return UserTypeEnum.retrieveTypeUserByValue(this.typeUserId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
