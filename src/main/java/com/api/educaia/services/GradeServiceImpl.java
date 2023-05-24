package com.api.educaia.services;

import com.api.educaia.dtos.GradeDTO;
import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.repositories.GradeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    private GradeRepository gradeRepository;
    @Override
    public void createGrade(GradeDTO gradeDTO) {
        GradeModel gradeModel = new GradeModel();
        BeanUtils.copyProperties(gradeDTO, gradeModel);
        gradeRepository.save(gradeModel);

    }

    @Override
    public List<GradeModel> listGrades() {
        return gradeRepository.findAll();
    }




}
