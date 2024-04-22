package com.api.educaia.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class EvaluationDTO {
    private UUID id;
    private String name;
    private String subjectId;
    private List<GradeDTO> grades;

    public EvaluationDTO(UUID id, String name, String subjectId, List<GradeDTO> grades) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.grades = grades;
    }
}
