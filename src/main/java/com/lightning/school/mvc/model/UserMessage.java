package com.lightning.school.mvc.model;

import com.lightning.school.mvc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserMessage")
@Table(name = "USER_MESSAGE")
public class UserMessage implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER_MESSAGE")
    private Integer userMessageId;
    @OneToOne
    private User sender;
    @OneToOne
    private User receiver;
    @OneToOne
    private Message message;

}
