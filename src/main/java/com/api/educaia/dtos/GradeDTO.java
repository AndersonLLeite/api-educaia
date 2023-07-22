package com.api.educaia.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;
@Getter
@NoArgsConstructor
public class GradeDTO {
        @NotBlank
        private String name;
        @NotBlank
        private String evaluationId;
        @NotBlank
        private String userId;
        @NotBlank
        private double grade;
        @NotBlank
        private String status;

        public GradeDTO(String name, String evaluationId, String userId, double grade, String status) {
            this.name = name;
            this.evaluationId = evaluationId;
            this.userId = userId;
            this.grade = grade;
            this.status = status;
        }


    }

