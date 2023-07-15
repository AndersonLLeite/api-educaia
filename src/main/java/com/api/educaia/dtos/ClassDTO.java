package com.api.educaia.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String schoolId;
    @JsonIgnore
    private List<SubjectDTO> subjects;

    public ClassDTO(String name, String schoolId) {
        this.name = name;
        this.schoolId = schoolId;
    }
}

