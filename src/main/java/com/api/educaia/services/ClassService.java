package com.api.educaia.services;

import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.ClassModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassService {
    ClassModel createClass(ClassModel classModel);

    List<ClassModel> listClasses();

    void createSubject(SubjectDTO subjectDTO, UUID classID);

    void deleteClass(UUID classId);

    Optional<ClassModel> getClassByClassId(UUID classId);

    void deleteSubjectFromSubjectsList(ClassModel classModel, UUID subjectId);
}
