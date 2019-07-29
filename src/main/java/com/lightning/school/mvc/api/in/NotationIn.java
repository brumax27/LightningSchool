package com.lightning.school.mvc.api.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotationIn {

    private Float mark;
    private Integer userId;
    private Integer exerciceId;
}
