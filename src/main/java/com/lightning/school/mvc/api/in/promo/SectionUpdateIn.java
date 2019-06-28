package com.lightning.school.mvc.api.in.promo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionUpdateIn {

    private Integer id;
    private String sectionLabel;
    private List<Integer> userIds;
    private List<Integer> coursIds;


}
