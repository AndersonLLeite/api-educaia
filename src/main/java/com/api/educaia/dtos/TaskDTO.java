package com.api.educaia.dtos;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

public class TaskDTO  {
    private String subject;
    private String teacherName;
    private String title;
    private String description;
    private Long deadLineDate;
    private Long creationDate;

    public Long getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(Long deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

//
}