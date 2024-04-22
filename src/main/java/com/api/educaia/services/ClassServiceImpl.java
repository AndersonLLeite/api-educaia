package com.api.educaia.services;

import com.api.educaia.dtos.AvgClassDTO;
import com.api.educaia.dtos.AvgSubjectsDTO;
import com.api.educaia.dtos.ClassIdentifierDTO;
import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.repositories.ClassRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Override
    public Optional<ClassModel> getClassById(UUID classId) {
        return classRepository.findById(classId);
    }

    @Override
    public List<AvgSubjectsDTO> getListAvgSubjects(List<SubjectModel> subjects) {
            List<AvgSubjectsDTO> avgSubjects = new ArrayList<>();
            for (SubjectModel subject : subjects) {
                double avg = subject.getAvg();
                AvgSubjectsDTO avgSubject = new AvgSubjectsDTO(subject.getName(), avg);
                avgSubjects.add(avgSubject);
            }

            return avgSubjects;
        }

    @Override
    public List<AvgClassDTO> getListAvgClasses(List<ClassModel> classes) {
        List<AvgClassDTO> avgClasses = new ArrayList<>();
        for (ClassModel classModel : classes) {
            double avg = classModel.getAvg();
            AvgClassDTO avgClass = new AvgClassDTO(classModel.getName(), avg);
            avgClasses.add(avgClass);
        }

        return avgClasses;
    }

    @Override
    public UUID addSubjectToSubjectsList(ClassModel classModel, SubjectDTO subjectDTO) {
        SubjectModel subjectModel = new SubjectModel();
        BeanUtils.copyProperties(subjectDTO, subjectModel);
        classModel.addSubject(subjectModel);
       classRepository.save(classModel);

        return classModel.getSubjects().get(classModel.getSubjects().size() - 1).getId();
    }

    @Override
    public List<ClassModel> getClassesByTeacherIdAndSchoolId(String teacherId, String schoolId) {
        List<ClassModel> classes = classRepository.findAllBySchoolId(schoolId);
        List<ClassModel> classesByTeacherId = new ArrayList<>();
        for (ClassModel classModel : classes) {
            for (SubjectModel subjectModel : classModel.getSubjects()) {
                if (subjectModel.getTeacherId().equals(teacherId)) {
                    classesByTeacherId.add(classModel);
                    break;
                }
            }
        }
        return classesByTeacherId;
    }

    @Override
    public List<ClassIdentifierDTO> getClassesIdentifierByClassesModel(List<ClassModel> classes) {
        List<ClassIdentifierDTO> classIdentifierDTOS = new ArrayList<>();
        for (ClassModel classModel : classes) {
            ClassIdentifierDTO classIdentifierDTO = new ClassIdentifierDTO(classModel.getId(), classModel.getName());

            classIdentifierDTOS.add(classIdentifierDTO);
        }
        return classIdentifierDTOS;
    }


}
