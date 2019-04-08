package com.lightning.school.mvc.model;

import java.util.Arrays;

public enum TypeUserEnum {

    ADMIN(1),
    TEACHER(2),
    STUDENT(3);

    private Integer value;

    TypeUserEnum(Integer value) {
        this.value = value;
    }

    public static TypeUserEnum retrieveTypeUserByValue(Integer value){
        return Arrays.stream(TypeUserEnum.values())
                .filter( tue -> tue.value == value)
                .findFirst()
                .get();
    }

    public static Integer retrieveValueByUserType(TypeUserEnum typeUserEnum){
        return Arrays.stream(TypeUserEnum.values())
                .filter( tue -> tue.equals(typeUserEnum))
                .findFirst()
                .get().value;
    }
}
