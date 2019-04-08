package com.lightning.school.mvc.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserKey implements Serializable {

    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "MAIL", nullable = false)
    private String mail;

}
