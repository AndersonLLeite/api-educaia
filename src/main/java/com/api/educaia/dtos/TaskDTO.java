package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO  {

    private String subject;
    @NotBlank
    private String teacherName;
    @NotBlank
    private String title;
    private String description;
    private Long deadLineDate;
    @NotNull
    private Long creationDate;

}