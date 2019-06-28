package com.lightning.school.mvc.api.out;

import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.model.user.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserItem {

    private Integer id;
    private String mail;
    private UserTypeEnum userTypeEnum;
    private String name;
    private String surmane;
    private String userPhoto;

    public UserItem(User user) {
        this.id = user.getUserId();
        this.mail = user.getMail();
        this.userTypeEnum = user.getUserType();
        this.name = user.getName();
        this.surmane = user.getSurname();
        this.userPhoto = user.getUserPhoto();
    }
}
