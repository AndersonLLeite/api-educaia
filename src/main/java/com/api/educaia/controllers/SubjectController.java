package com.api.educaia.controllers;

import com.api.educaia.dtos.GradeDTO;
import com.api.educaia.dtos.SubjectDTO;
import com.api.educaia.dtos.SubjectIdentifierDTO;
import com.api.educaia.models.ClassModel;
import com.api.educaia.models.GradeModel;
import com.api.educaia.models.SubjectModel;
import com.api.educaia.services.ClassService;
import com.api.educaia.services.GradeService;
import com.api.educaia.services.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ClassService classService;

@PostMapping("/create-subject-by-classID/{classId}")
    public ResponseEntity<?> createSubject(@PathVariable UUID classID, @RequestBody SubjectDTO subjectDTO){
        try {
            subjectService.createSubject(subjectDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

@GetMapping("/list-subjects")
    public ResponseEntity<?> listSubjects(){
        return ResponseEntity.ok(subjectService.listSubjects());
    }

    @PostMapping("/create-grade")
    public ResponseEntity<?> createGrade(@RequestBody GradeDTO gradeDTO){
        try {
            gradeService.createGrade(gradeDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list-grades")
    public ResponseEntity<?> listGrades(){
        return ResponseEntity.ok(gradeService.listGrades());
    }

    @GetMapping("/get-subjects-identifier-by-classId/{classId}")
    public ResponseEntity<?> getSubjectsByClassId(@PathVariable String classId){
        List<SubjectModel> subjectModels = subjectService.getSubjectsByClassId(classId);
        List<SubjectIdentifierDTO> subjectsIdentifierDTO = subjectService.getSubjectsIdentifierBySubjectsModel(subjectModels);
        return ResponseEntity.ok(subjectService.getSubjectsByClassId(classId));
    }

    @PostMapping("/create-grade-by-subjectId/{subjectId}")
    public ResponseEntity<?> createGradeBySubjectId(@PathVariable UUID subjectId, @RequestBody GradeDTO gradeDTO){
        try {
            SubjectModel subjectModel = subjectService.getSubjectBySubjectId(subjectId);
            GradeModel gradeModel = new GradeModel();
            BeanUtils.copyProperties(gradeDTO, gradeModel);
            subjectService.addGradeToSubject(subjectModel, gradeModel);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-grades-by-subjectId/{subjectId}")
    public ResponseEntity<?> getGradesBySubjectId(@PathVariable UUID subjectId){
        try {
            SubjectModel subjectModel = subjectService.getSubjectBySubjectId(subjectId);
            return ResponseEntity.ok(subjectModel.getGrades());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-subject-by-subjectId/{subjectId}")
    public ResponseEntity<?> getSubjectBySubjectId(@PathVariable UUID subjectId){
        try {
            SubjectModel subjectModel = subjectService.getSubjectBySubjectId(subjectId);
            List<GradeDTO> gradesDTO = gradeService.getGradesDTOByGradesModel(subjectModel.getGrades());
            SubjectDTO subjectDTO = new SubjectDTO(subjectModel.getId(), subjectModel.getName(), subjectModel.getSchoolId(), subjectModel.getClassId(), gradesDTO);
            return ResponseEntity.ok(subjectDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-subject-by-subjectId-and-classId/{subjectId}/{classId}")
    public ResponseEntity<?> deleteSubjectBySubjectId(@PathVariable UUID subjectId, @PathVariable UUID classId){
        Optional<ClassModel> classModelOp = classService.getClassByClassId(classId);
        if(!classModelOp.isPresent()){
            return ResponseEntity.badRequest().body("Class not found");
        }
        ClassModel classModel = classModelOp.get();
        try {
            classService.deleteSubjectFromSubjectsList(classModel, subjectId);
            subjectService.deleteSubjectBySubjectId(subjectId);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }




}
