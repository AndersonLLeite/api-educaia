package com.api.educaia.controllers;

import com.api.educaia.dtos.QuizDTO;
import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.QuizModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.services.AssistantService;
import com.api.educaia.services.QuizService;
import com.api.educaia.services.RateService;
import com.api.educaia.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private AssistantService assistantService;
    @Autowired
    private RateService rateService;

    @Autowired
    private QuizService quizService;

    //    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
//    public ResponseEntity<?> createTask(@RequestBody @Valid  TaskDTO taskDTO) {
//        var taskModel = new TaskModel();
//        BeanUtils.copyProperties(taskDTO, taskModel);
//        TaskModel taskModelResponse  = taskService.createTask(taskModel);
//        rateService.createRateModel(taskModelResponse.getId());
//        quizService.createQuiz(taskModelResponse.getId());
//
//
//        return new ResponseEntity<TaskModel>(taskModelResponse, HttpStatus.CREATED);
//    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list-tasks", method = RequestMethod.GET)
    public ResponseEntity<?> listTasks() {

        List<TaskModel> tasks = taskService.listTasks();
        return new ResponseEntity<List<TaskModel>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/list-tasks/{creationDate}/{classId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTasksByCreationDate(@PathVariable("creationDate") Long creationDate, @PathVariable("classId") String classId) {
        List<TaskModel> tasks = taskService.getTasksByCreationDateAndClassId(creationDate, classId);
        List<TaskDTO> taskDTOS = taskService.convertTaskModelToTaskDTO(tasks);
        return new ResponseEntity<List<TaskDTO>>(taskDTOS, HttpStatus.OK);
    }

    @GetMapping("/countByClassIdAndToday/{classId}")
    public ResponseEntity<Long> countTasksByClassIdAndToday(@PathVariable String classId) {
        LocalDate today = LocalDate.now();
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        long todayMillis = today.atStartOfDay(zoneId).toEpochSecond() * 1000;

        Long count = taskService.countTasksByClassIdAndCreationDate(classId, todayMillis);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/getQuiz/{taskId}")
    public ResponseEntity<?> getQuizQuestions(@PathVariable UUID taskId) {
        Optional<TaskModel> taskOp = taskService.getTaskById(taskId);
        if (!taskOp.isPresent()) {
            return new ResponseEntity<String>("Task not found", HttpStatus.NOT_FOUND);
        }
        TaskModel task = taskOp.get();
        QuizDTO quizDTO = taskService.getQuiz(task);

        return ResponseEntity.ok(quizDTO);
    }

    @PostMapping("/createQuiz/{taskId}")
    public ResponseEntity<?> createQuiz(@PathVariable UUID taskId, @RequestBody QuizDTO quizDTO) {

        Optional<TaskModel> taskOp = taskService.getTaskById(taskId);
        if (!taskOp.isPresent()) {
            return new ResponseEntity<String>("Task not found", HttpStatus.NOT_FOUND);
        }
        TaskModel task = taskOp.get();
        QuizModel quiz = task.getQuiz();
        if (quiz != null) {
            return new ResponseEntity<String>("Quiz already exists", HttpStatus.BAD_REQUEST);
        }
        QuizDTO quizDTOResponse = taskService.createQuiz(quizDTO, task);
        return ResponseEntity.ok(quizDTOResponse);
    }

    @PostMapping("/addQuestion/{taskId}")
    public ResponseEntity<?> addQuestion(@PathVariable UUID taskId, @RequestBody QuizQuestionDTO quizQuestionDTO) {
        Optional<TaskModel> taskOp = taskService.getTaskById(taskId);
        if (!taskOp.isPresent()) {
            return new ResponseEntity<String>("Task not found", HttpStatus.NOT_FOUND);
        }
        TaskModel task = taskOp.get();
        QuizModel quiz = task.getQuiz();
        if (quiz == null) {
            return new ResponseEntity<String>("Quiz not found", HttpStatus.NOT_FOUND);
        }
        QuizQuestionDTO quizQuestionDTOResponse = taskService.addQuestion(task, quizQuestionDTO);
        return ResponseEntity.ok(quizQuestionDTOResponse);
    }
}














