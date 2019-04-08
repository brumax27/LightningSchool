package com.lightning.school.mvc.model.exercice;

import java.util.Arrays;

public enum  ExerciceTypeEnum {
    TEACHER(1),
    STUDENT(2);

    private Integer value;

    ExerciceTypeEnum(Integer value) {
        this.value = value;
    }

    public static ExerciceTypeEnum retrieveExercieTypeEnumByValue(Integer value) {
        return Arrays.stream(ExerciceTypeEnum.values())
                .filter( exerciceType -> exerciceType.value == value)
                .findFirst()
                .get();
    }

    public static ExerciceTypeEnum retrieveExercieTypeEnumByValue(ExerciceTypeEnum exerciceType) {
        return Arrays.stream(ExerciceTypeEnum.values())
                .filter( exerciceTypeEnum -> exerciceTypeEnum.equals(exerciceType))
                .findFirst()
                .get();
    }
}
