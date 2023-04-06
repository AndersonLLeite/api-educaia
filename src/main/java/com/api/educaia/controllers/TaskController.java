package com.api.educaia.controllers;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/create-task/", method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        TaskModel taskModelResponse  = taskService.createTask(taskModel);

        return new ResponseEntity<TaskModel>(taskModelResponse, HttpStatus.CREATED);
    }
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


}