package com.api.educaia.controllers;

import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.RateQuestionModel;
import com.api.educaia.models.RateModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.services.RateService;
import com.api.educaia.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private RateService rateService;

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody @Valid  TaskDTO taskDTO) {
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        TaskModel taskModelResponse  = taskService.createTask(taskModel);
        rateService.createRateModel(taskModelResponse.getId());

        return new ResponseEntity<TaskModel>(taskModelResponse, HttpStatus.CREATED);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list-tasks", method = RequestMethod.GET)
    public ResponseEntity<?> listTasks() {

        List<TaskModel> tasks = taskService.listTasks();
        return new ResponseEntity<List<TaskModel>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/list-tasks/{creationDate}", method = RequestMethod.GET)
    public ResponseEntity<?> getTasksByCreationDate(@PathVariable("creationDate") Long creationDate) {
        List<TaskModel> tasks = taskService.getTasksByCreationDate(creationDate);
        return new ResponseEntity<List<TaskModel>>(tasks, HttpStatus.OK);
    }
    @RequestMapping(value = "/task-quiz-finished/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> taskQuizFinished(@PathVariable("id") UUID id) {
        Optional<TaskModel> taskOp = taskService.getTaskById(id);


        if (!taskOp.isPresent()) {
            return new ResponseEntity<String>("Task not found", HttpStatus.NOT_FOUND);
        }

        TaskModel task = taskOp.get();
        task.setQuizIsDone(true);
        taskService.saveTask(task);
        System.out.println(task);
        return new ResponseEntity<TaskModel>(task, HttpStatus.OK);
    }



    @GetMapping("/countByClassIdAndToday/{classId}")
    public ResponseEntity<Long> countTasksByClassIdAndToday(@PathVariable String classId) {
        LocalDate today = LocalDate.now();
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        long todayMillis = today.atStartOfDay(zoneId).toEpochSecond() * 1000;

        Long count = taskService.countTasksByClassIdAndCreationDate(classId, todayMillis);
        return ResponseEntity.ok(count);
    }










}