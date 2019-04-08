package com.lightning.school.mvc.model;

import com.lightning.school.mvc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_MESSAGE")
public class UserMessage implements Serializable {

    @OneToOne
    private User sender;
    @OneToOne
    private User receiver;
    @OneToOne
    private Message message;

}
