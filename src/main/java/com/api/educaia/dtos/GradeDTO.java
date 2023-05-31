package com.api.educaia.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
        @NotBlank
        private String name;
        @NotBlank
        private String userId;
        @NotBlank
        private double grade;
        @NotBlank
        private String status;


    }
