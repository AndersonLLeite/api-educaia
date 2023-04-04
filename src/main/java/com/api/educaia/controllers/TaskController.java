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
@RequestMapping("/educaia")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/task/", method = RequestMethod.POST)
    public ResponseEntity<?> criarTask(@RequestBody TaskDTO taskDTO) {
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        TaskModel taskModelResponse  = taskService.save(taskModel);

        return new ResponseEntity<Long>(taskModelResponse.getId(), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<?> listarTasks() {

        List<TaskModel> tasks = taskService.listarTasks();

        if (tasks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<TaskModel>>(tasks, HttpStatus.OK);
    }


}