package com.api.educaia.services;

import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.dtos.SubjectIdentifierDTO;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;

import java.util.List;
import java.util.UUID;

public interface SubjectService {
    void createSubject(SubjectDTO subjectDTO);

    List<SubjectModel> listSubjects();

    List<SubjectModel> getSubjectsByClassId(String classId);

    SubjectModel getSubjectBySubjectId(UUID subjectId);

    void addGradeToSubject(SubjectModel subjectModel, GradeModel gradeModel);

    List<SubjectIdentifierDTO> getSubjectsIdentifierBySubjectsModel(List<SubjectModel> subjectModels);

    void deleteSubjectBySubjectId(java.util.UUID subjectId);
}
