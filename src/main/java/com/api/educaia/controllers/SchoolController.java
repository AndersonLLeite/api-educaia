package com.api.educaia.controllers;

import com.api.educaia.dtos.SchoolDTO;
import com.api.educaia.dtos.UserDTO;
import com.api.educaia.models.SchoolModel;
import com.api.educaia.models.UserModel;
import com.api.educaia.services.SchoolService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/create-school", method = RequestMethod.POST)
    public ResponseEntity<?> createSchool(@RequestBody @Valid  SchoolDTO schoolDTO) {
        System.out.println("SchoolDTO: " + schoolDTO);
        var schoolModel = new SchoolModel();
        BeanUtils.copyProperties(schoolDTO, schoolModel);
        SchoolModel schoolModelResponse  = schoolService.createSchool(schoolModel);

        return new ResponseEntity<SchoolModel>(schoolModelResponse, HttpStatus.CREATED);
    }
}
