package com.api.educaia.services;

import com.api.educaia.models.ClassModel;
import com.api.educaia.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassRepository classRepository;

    @Transactional
    @Override
    public ClassModel createClass(ClassModel classModel) {
        return classRepository.save(classModel);
    }

    @Override
    public List<ClassModel> listClasses() {
        return classRepository.findAll();
    }
}
