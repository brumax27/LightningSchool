package com.lightning.school.mvc.api.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursUpdateIn {

    private Integer id;
    private String coursLabel;
    private LocalDateTime deadline;
    private List<Integer> exerciceIds;

}
