package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String schoolId;
}
