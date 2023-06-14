package com.api.educaia.services;

import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.dtos.SubjectIdentifierDTO;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.repositories.SubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectServiceImpl implements  SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void createSubject(SubjectDTO subjectDTO) {
        SubjectModel subjectModel = new SubjectModel();
        BeanUtils.copyProperties(subjectDTO, subjectModel);
        subjectRepository.save(subjectModel);
    }

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
    public void addGradeToSubject(SubjectModel subjectModel, GradeModel gradeModel) {
        subjectModel.add(gradeModel);
        subjectRepository.save(subjectModel);
    }

    @Override
    public List<SubjectIdentifierDTO> getSubjectsIdentifierBySubjectsModel(List<SubjectModel> subjectModels) {
        List<SubjectIdentifierDTO> subjectIdentifierDTOS = new ArrayList<>();
        for (SubjectModel subjectModel : subjectModels) {
            SubjectIdentifierDTO subjectIdentifierDTO = new SubjectIdentifierDTO(subjectModel.getId() , subjectModel.getName());

            subjectIdentifierDTOS.add(subjectIdentifierDTO);
        }
        return subjectIdentifierDTOS;
    }

    @Override
    public void deleteSubjectBySubjectId(UUID subjectId) {
        subjectRepository.deleteById(subjectId);
    }


}
