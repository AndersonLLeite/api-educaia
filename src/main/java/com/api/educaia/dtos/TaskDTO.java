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

    //crie o construtor
//    public TaskDTO(UUID id, String subjectName, String teacherName, String title, String description, Long deadLineDate, Long creationDate, String classId, String schoolId) {
//        this.id = id;
//        this.subjectName = subjectName;
//        this.teacherName = teacherName;
//        this.title = title;
//        this.description = description;
//        this.deadLineDate = deadLineDate;
//        this.creationDate = creationDate;
//        this.classId = classId;
//        this.schoolId = schoolId;
//    }

}