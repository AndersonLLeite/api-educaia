package com.api.educaia.dtos;

import com.api.educaia.models.GradeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    @JsonIgnore
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String schoolId;
    @NotBlank
    private String classId;
    private String teacherName;
    private List<GradeDTO> grades;
}
