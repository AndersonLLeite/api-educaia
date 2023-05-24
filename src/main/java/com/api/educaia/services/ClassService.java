package com.api.educaia.services;

import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.ClassModel;

import java.util.List;
import java.util.UUID;

public interface ClassService {
    ClassModel createClass(ClassModel classModel);

    List<ClassModel> listClasses();

    void createSubject(SubjectDTO subjectDTO, UUID classID);
}
