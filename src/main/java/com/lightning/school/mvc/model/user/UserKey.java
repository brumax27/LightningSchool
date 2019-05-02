package com.lightning.school.mvc.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserKey implements Serializable {

    @Column(name = "USER_ID")
    private String userId;

    @Email
    @Column(name = "MAIL", nullable = false)
    private String mail;

    public UserKey(String userId) {
        this.userId = userId;
    }
}
