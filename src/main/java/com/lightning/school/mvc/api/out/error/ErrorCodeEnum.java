package com.lightning.school.mvc.api.out.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCodeEnum {

    CRUD_EXCEPTION(1),
    MAIL_CUSTOM_EXCEPTION(2),
    USER_NOT_FOUNDED(3),
    PASSWORD_INVALID_EXCEPTION(4),
    AUTH_EXCEPTION(5),
    SERVLET_EXCEPTION(6),
    USER_EXISTED_EXCEPTION(7),
    NO_DATA_EXCEPTION(8),
    BAD_USER_EXCEPTION(9),
    MISSING_OPERATOR_EXCEPTION(10),
    MISSING_OPERAND_EXCEPTION(11),
    NPI_EMPTY_EXCEPTION(12),
    OPERATOR_NOT_SUPORTED(13);


    @Getter
    private Integer code;

}
