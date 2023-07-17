package com.api.educaia.services;

import com.api.educaia.dtos.AvgClassDTO;
import com.api.educaia.dtos.AvgSubjectsDTO;
import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.SubjectModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassService {
    ClassModel createClass(ClassModel classModel);

    List<ClassModel> listClasses();
    void deleteClass(UUID classId);

    Optional<ClassModel> getClassByClassId(UUID classId);

    void deleteSubjectFromSubjectsList(ClassModel classModel, UUID subjectId);

    List<ClassModel> listClassesBySchoolId(String schoolId);

    Optional<ClassModel> getClassById(UUID classId);

    List<AvgSubjectsDTO> getListAvgSubjects(List<SubjectModel> subjects);

    List<AvgClassDTO> getListAvgClasses(List<ClassModel> classes);

    UUID addSubjectToSubjectsList(ClassModel classModel, SubjectDTO subjectDTO);
}
