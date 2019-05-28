package com.lightning.school.mvc.api.in.promo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionCreateIn {

    private String sectionLabel;
    private Integer promotionYear;

}
