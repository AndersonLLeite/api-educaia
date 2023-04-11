package com.api.educaia.controllers;

import com.api.educaia.dtos.ClassDTO;
import com.api.educaia.dtos.SchoolDTO;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.SchoolModel;
import com.api.educaia.services.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @RequestMapping(value = "/create-class", method = RequestMethod.POST)
    public ResponseEntity<?> createClass(@RequestBody @Valid ClassDTO classDTO) {
        var classModel = new ClassModel();
        BeanUtils.copyProperties(classDTO, classModel);
        ClassModel classModelResponse  = classService.createClass(classModel);

        return new ResponseEntity<ClassModel>(classModelResponse, HttpStatus.CREATED);
    }
}
