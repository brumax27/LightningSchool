package com.lightning.school.mvc.api.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursCreateIn {

    private String coursLabel;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime deadline;

}
