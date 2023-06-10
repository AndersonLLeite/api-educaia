package com.api.educaia.services;

import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.repositories.ClassRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassRepository classRepository;

    @Transactional
    @Override
    public ClassModel createClass(ClassModel classModel) {
        return classRepository.save(classModel);
    }

    //TODO - apenas para testes
    @Override
    public List<ClassModel> listClasses() {
        return classRepository.findAll();
    }

    @Override
    public void createSubject(SubjectDTO subjectDTO, UUID classID) {
        ClassModel classModel = classRepository.findById(classID).orElseThrow(() -> new RuntimeException("Class not found"));
        SubjectModel subjectModel = new SubjectModel();
        BeanUtils.copyProperties(subjectDTO, subjectModel);
        classModel.addSubject(subjectModel);
        classRepository.save(classModel);
    }

    @Override
    public void deleteClass(UUID classId) {
        classRepository.deleteById(classId);
    }

    @Override
    public Optional<ClassModel> getClassByClassId(UUID classId) {
        return classRepository.findById(classId);
    }

    @Override
    public void deleteSubjectFromSubjectsList(ClassModel classModel, UUID subjectId) {
        classModel.removeSubject(subjectId);
        classRepository.save(classModel);
    }

    @Override
    public List<ClassModel> listClassesBySchoolId(String schoolId) {
        return classRepository.findAllBySchoolId(schoolId);
    }
}
