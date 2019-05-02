package com.lightning.school.mvc.api.out.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    @Getter
    private String message;
    @Getter
    private int errorCode;

    public ErrorDto(String message) {
        this.message = message;
    }
}
