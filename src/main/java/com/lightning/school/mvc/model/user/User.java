package com.lightning.school.mvc.model.user;

import com.lightning.school.mvc.model.Group;
import com.lightning.school.mvc.model.Section;
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

@Table(name = "USER")
public class User implements Serializable {

    @EmbeddedId
    private UserKey userKey;
    @Column(name = "ID_T_USER")
    private Integer typeUserId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHOTO_PATH")
    private String userPhoto;

    @ManyToMany
    private List<Section> sections;

    @ManyToMany
    List<Group> groups;

    public UserTypeEnum getUserType(){
        return UserTypeEnum.retrieveTypeUserByValue(this.typeUserId);
    }

}
