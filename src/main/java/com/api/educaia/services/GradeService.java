package com.api.educaia.services;

import com.api.educaia.dtos.GradeDTO;
import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.GradeModel;

import java.util.List;
import java.util.UUID;

public interface GradeService {
     void createGrade(GradeDTO gradeDTO);

    List<GradeModel> listGrades();

    List<GradeDTO> getGradesDTOByGradesModel(List<GradeModel> grades);
}
