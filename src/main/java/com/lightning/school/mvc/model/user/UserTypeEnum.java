package com.lightning.school.mvc.model.user;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;

public enum UserTypeEnum implements Serializable {

    ADMIN(1),
    TEACHER(2),
    STUDENT(3);

    @Getter
    private Integer value;

    UserTypeEnum(Integer value) {
        this.value = value;
    }

    public static UserTypeEnum retrieveTypeUserByValue(Integer value){
        return Arrays.stream(UserTypeEnum.values())
                .filter( tue -> tue.value == value)
                .findFirst()
                .get();
    }

    public static Integer retrieveValueByUserType(UserTypeEnum userTypeEnum){
        return Arrays.stream(UserTypeEnum.values())
                .filter( tue -> tue.equals(userTypeEnum))
                .findFirst()
                .get().value;
    }
}
