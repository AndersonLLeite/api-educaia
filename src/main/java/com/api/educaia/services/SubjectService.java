package com.api.educaia.services;

import com.api.educaia.dtos.*;
import com.api.educaia.models.EvaluationModel;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;

import java.util.List;
import java.util.UUID;

public interface SubjectService {

    List<SubjectModel> listSubjects();

    List<SubjectModel> getSubjectsByClassId(String classId);

    SubjectModel getSubjectBySubjectId(UUID subjectId);

    void addGradeToSubjectEvaluation(SubjectModel subjectModel, GradeModel gradeModel);

    List<SubjectIdentifierDTO> getSubjectsIdentifierBySubjectsModel(List<SubjectModel> subjectModels);


    void assignTeacherToSubject(UUID subjectId, UserIdentifierDTO teacher);

    List<SubjectModel> getSubjectsByClassIdAndTeacherId(String classId, String teacherId);

    List<EvaluationDTO> getEvaluationsDTOByEvaluationsModel(List<EvaluationModel> evaluations);

    void addEvaluationToSubjectEvaluation(SubjectModel subjectModel, EvaluationDTO evaluationDTO);

    List<EvaluationModel> listEvaluations();
}
