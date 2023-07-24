package com.api.educaia.services;

import com.api.educaia.dtos.*;
import com.api.educaia.models.EvaluationModel;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.repositories.EvaluationRepository;
import com.api.educaia.repositories.SubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectServiceImpl implements  SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public List<SubjectModel> listSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<SubjectModel> getSubjectsByClassId(String classId) {
        return subjectRepository.findByClassId(classId);
    }

    @Override
    public SubjectModel getSubjectBySubjectId(UUID subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }

    @Override
    public UUID addGradeToSubjectEvaluation(UUID evaluationId, GradeDTO gradeDTO) {

            Optional<EvaluationModel> evaluationModelOp = evaluationRepository.findById(evaluationId);
            if (!evaluationModelOp.isPresent()) {
                throw new RuntimeException("Evaluation not found");
            }
            EvaluationModel evaluationModel = evaluationModelOp.get();
            GradeModel gradeModel = new GradeModel();
            BeanUtils.copyProperties(gradeDTO, gradeModel);
            evaluationModel.addGrade(gradeModel);
            return evaluationRepository.save(evaluationModel).getGrades().get(evaluationModel.getGrades().size() - 1).getId();
    }

    @Override
    public List<SubjectIdentifierDTO> getSubjectsIdentifierBySubjectsModel(List<SubjectModel> subjectModels) {
        List<SubjectIdentifierDTO> subjectIdentifierDTOS = new ArrayList<>();
        for (SubjectModel subjectModel : subjectModels) {
            SubjectIdentifierDTO subjectIdentifierDTO = new SubjectIdentifierDTO(subjectModel.getId() , subjectModel.getName(), subjectModel.getTeacherName());
            subjectIdentifierDTOS.add(subjectIdentifierDTO);
        }
        return subjectIdentifierDTOS;
    }


    @Override
    public void assignTeacherToSubject(UUID subjectId, UserIdentifierDTO teacher) {
        SubjectModel subjectModel = subjectRepository.findById(subjectId).orElseThrow();
        subjectModel.setTeacherId(teacher.getId().toString());
        subjectModel.setTeacherName(teacher.getNameComplete());
        subjectRepository.save(subjectModel);
    }

    @Override
    public List<SubjectModel> getSubjectsByClassIdAndTeacherId(String classId, String teacherId) {
        return subjectRepository.findByClassIdAndTeacherId(classId, teacherId);

    }

    @Override
    public List<EvaluationDTO> getEvaluationsDTOByEvaluationsModel(List<EvaluationModel> evaluations) {
        List<EvaluationDTO> evaluationDTOS = new ArrayList<>();
        for (EvaluationModel evaluation : evaluations) {
            EvaluationDTO evaluationDTO = new EvaluationDTO(evaluation.getId(), evaluation.getName(), evaluation.getSubjectId(), getGradesModelToGradesDTO(evaluation.getGrades()));
            evaluationDTOS.add(evaluationDTO);
        }

        return evaluationDTOS;
    }

    private List<GradeDTO> getGradesModelToGradesDTO(List<GradeModel> grades) {
        List<GradeDTO> gradeDTOS = new ArrayList<>();
        for (GradeModel grade : grades) {
            GradeDTO gradeDTO = new GradeDTO(grade.getId(), grade.getName(), grade.getEvaluationId(), grade.getUserId(), grade.getGrade(), grade.getStatus());
            gradeDTOS.add(gradeDTO);
        }
        return gradeDTOS;
    }

    @Override
    public UUID addEvaluationToSubjectEvaluation(SubjectModel subjectModel, EvaluationDTO evaluationDTO) {
        EvaluationModel evaluationModel = new EvaluationModel();
        BeanUtils.copyProperties(evaluationDTO, evaluationModel);
        subjectModel.addEvaluation(evaluationModel);
        return evaluationRepository.save(evaluationModel).getId();
    }

    @Override
    public List<EvaluationModel> listEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public void deleteEvaluation( UUID subjectId, String evaluationId) {
        Optional<SubjectModel> subjectModelOp = subjectRepository.findById(subjectId);
        if (!subjectModelOp.isPresent()){
            throw new RuntimeException("Subject not found");
        }
        SubjectModel subjectModel = subjectModelOp.get();
        subjectModel.deleteEvaluation(evaluationId);
        subjectRepository.save(subjectModel);
    }


}
