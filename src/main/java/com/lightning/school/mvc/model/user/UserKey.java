package com.lightning.school.mvc.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserKey implements Serializable {

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "MAIL", nullable = false)
    private String mail;

}
