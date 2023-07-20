package com.api.educaia.services;

import com.api.educaia.dtos.AvgClassDTO;
import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.dtos.SubjectIdentifierDTO;
import com.api.educaia.dtos.UserIdentifierDTO;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;

import java.util.List;
import java.util.UUID;

public interface SubjectService {

    List<SubjectModel> listSubjects();

    List<SubjectModel> getSubjectsByClassId(String classId);

    SubjectModel getSubjectBySubjectId(UUID subjectId);

    void addGradeToSubject(SubjectModel subjectModel, GradeModel gradeModel);

    List<SubjectIdentifierDTO> getSubjectsIdentifierBySubjectsModel(List<SubjectModel> subjectModels);


    void assignTeacherToSubject(UUID subjectId, UserIdentifierDTO teacher);

    List<SubjectModel> getSubjectsByClassIdAndTeacherId(String classId, String teacherId);
}
