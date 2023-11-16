package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO  {
    private UUID id;
    private String subjectName;
    @NotBlank
    private String teacherName;
    @NotBlank
    private String title;
    private String description;
    private Long deadLineDate;
    @NotNull
    private Long creationDate;
    private String classId;
    private String schoolId;


}