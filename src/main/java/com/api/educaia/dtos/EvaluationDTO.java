package com.api.educaia.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class EvaluationDTO {
    private String name;
    private String subjectId;
    private List<GradeDTO> grades;

    public EvaluationDTO(String name, String subjectId, List<GradeDTO> grades) {
        this.name = name;
        this.subjectId = subjectId;
        this.grades = grades;
    }
}
