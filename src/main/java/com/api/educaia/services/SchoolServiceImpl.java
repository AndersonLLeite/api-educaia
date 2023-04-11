package com.api.educaia.services;

import com.api.educaia.models.SchoolModel;
import com.api.educaia.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SchoolServiceImpl implements SchoolService{
    @Autowired
    private SchoolRepository schoolRepository;
    @Transactional
    @Override
    public SchoolModel createSchool(SchoolModel schoolModel) {
        return schoolRepository.save(schoolModel);
    }
}
