package com.lightning.school.mvc.api.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyExoIn {

    private String npi;
    private Integer exerciceTeacherId;

}
