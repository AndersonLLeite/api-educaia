package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassHappeningRightNowDTO {
    private String title;
    private Long startTime;
    private Long endTime;


}
