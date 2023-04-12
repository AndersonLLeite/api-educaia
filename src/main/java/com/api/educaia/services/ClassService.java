package com.api.educaia.services;

import com.api.educaia.models.ClassModel;

import java.util.List;

public interface ClassService {
    ClassModel createClass(ClassModel classModel);

    List<ClassModel> listClasses();
}
