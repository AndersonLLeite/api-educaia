package com.api.educaia.controllers;

import com.api.educaia.dtos.ClassDTO;
import com.api.educaia.dtos.SchoolDTO;
import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.SchoolModel;
import com.api.educaia.services.CalendarService;
import com.api.educaia.services.ClassService;
import com.api.educaia.services.SchoolService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CalendarService calendarService;

    @RequestMapping(value = "/create-class", method = RequestMethod.POST)
    public ResponseEntity<?> createClass(@RequestBody @Valid ClassDTO classDTO) {
        var classModel = new ClassModel();
        BeanUtils.copyProperties(classDTO, classModel);
        ClassModel classModelResponse = classService.createClass(classModel);

        return new ResponseEntity<ClassModel>(classModelResponse, HttpStatus.CREATED);
    }

    @GetMapping("/list-classes-by-schoolId/{schoolId}")
    public ResponseEntity<?> listClassesBySchoolId(@PathVariable String schoolId) {
        List<ClassModel> classModels = classService.listClassesBySchoolId(schoolId);
        return ResponseEntity.ok(classModels);
    }

    @GetMapping("/list-classes")
    public ResponseEntity<?> listClassesBySchoolId() {
        List<ClassModel> classModels = classService.listClasses();
        return ResponseEntity.ok(classModels);
    }

    @PostMapping("/create-subject-by-classId/{classId}")
    public ResponseEntity<?> createSubject(@PathVariable UUID classId, @RequestBody SubjectDTO subjectDTO) {


        try {
            classService.createSubject(subjectDTO, classId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-class/{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable UUID classId) {
        try {
            classService.deleteClass(classId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-class-happening-right-now/{classId}")
    public ResponseEntity<?> getClassHappeningRightNow(@PathVariable String classId)
    {

        return ResponseEntity.ok(calendarService.getClassHappeningRightNow(classId));
    }
}
