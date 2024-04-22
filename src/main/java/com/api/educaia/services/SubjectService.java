package com.api.educaia.services;

import com.api.educaia.dtos.*;
import com.api.educaia.models.EvaluationModel;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.models.TaskModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectService {

    List<SubjectModel> listSubjects();

    List<SubjectModel> getSubjectsByClassId(String classId);

    Optional<SubjectModel> getSubjectBySubjectId(UUID subjectId);

    UUID addGradeToSubjectEvaluation(UUID evaluationId, GradeDTO gradeDTO);

    List<SubjectIdentifierDTO> getSubjectsIdentifierBySubjectsModel(List<SubjectModel> subjectModels);


    void assignTeacherToSubject(UUID subjectId, UserIdentifierDTO teacher);

    List<SubjectModel> getSubjectsByClassIdAndTeacherId(String classId, String teacherId);

    List<EvaluationDTO> getEvaluationsDTOByEvaluationsModel(List<EvaluationModel> evaluations);

    UUID addEvaluationToSubjectEvaluation(SubjectModel subjectModel, EvaluationDTO evaluationDTO);

    List<EvaluationModel> listEvaluations();

    void deleteEvaluation(UUID subjectId, String evaluationId);

    TaskDTO addTaskToSubjectTasks(SubjectModel subjectModel, TaskModel taskModel);

    List<TaskDTO> getTasksBySubject(SubjectModel subjectModel);

    void deleteTask(SubjectModel subjectModel, UUID taskId);

    void updateTask(SubjectModel subjectModel, TaskDTO taskDTO);
}
