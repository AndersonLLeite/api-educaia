package com.api.educaia.controllers;

import com.api.educaia.dtos.*;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.models.UserModel;
import com.api.educaia.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

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
        List<ClassDTO> classDTOS = new ArrayList<>();
        for (ClassModel classModel : classModels) {
            ClassDTO classDTO = new ClassDTO(classModel.getId().toString(), classModel.getName());

            classDTOS.add(classDTO);
        }
        return ResponseEntity.ok(classDTOS);
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

    @GetMapping("/get-class-by-id/{classId}")
    public ResponseEntity<?> getClassById(@PathVariable UUID classId)
    {
        Optional<ClassModel> classModel = classService.getClassById(classId);
        if(classModel.isEmpty())
        {
            return ResponseEntity.badRequest().body("Class not found");
        }
        return ResponseEntity.ok(classModel.get());
    }

    @GetMapping("/get-students-by-classId/{classId}")
    public ResponseEntity<?> getStudentsByClassId(@PathVariable String classId)
    {
       List<UserModel> students = userService.getStudentsByClassId(classId);
       List<UserPublicDTO> studentsDTO = userService.getUsersPublic(students);

         return ResponseEntity.ok(studentsDTO);
    }

    @GetMapping("/get-list-avg-subjects-by-classId/{classId}")
    public ResponseEntity<?> getListAvgSubjectsByClassId(@PathVariable String classId)
    {
        List<SubjectModel> subjects = subjectService.getSubjectsByClassId(classId);
        List<AvgSubjectsDTO> listAvgSubjects = classService.getListAvgSubjects(subjects);
        return ResponseEntity.ok(listAvgSubjects);
    }

    @GetMapping("/get-list-avg-classes/{schoolId}")
    public ResponseEntity<?> getListAvgClasses(@PathVariable String schoolId)
    {
        List<ClassModel> classes = classService.listClassesBySchoolId(schoolId);
        List<AvgClassDTO> listAvgClasses = classService.getListAvgClasses(classes);
        return ResponseEntity.ok(listAvgClasses);
    }

    @GetMapping("/get-list-subjects-identifier-by-classId/{classId}")
    public ResponseEntity<?> getSubjectsIdentifierByClassId(@PathVariable String classId)
    {
        List<SubjectModel> subjects = subjectService.getSubjectsByClassId(classId);
        System.out.print(subjects.get(0).getName());

        List<SubjectIdentifierDTO> subjectsIdentifier = subjectService.getSubjectsIdentifierBySubjectsModel(subjects);
        return ResponseEntity.ok(subjectsIdentifier);
    }

    @GetMapping("/get-list-students-identifier-by-classId/{classId}")
    public ResponseEntity<?> getStudentsIdentifierByClassId(@PathVariable String classId)
    {
        List<UserModel> students = userService.getStudentsByClassId(classId);
        List<UserIdentifierDTO> studentsIdentifier = userService.getUsersIdentifier(students);
        return ResponseEntity.ok(studentsIdentifier);
    }




}
