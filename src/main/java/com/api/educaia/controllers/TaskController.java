package com.api.educaia.controllers;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.RateQuestion;
import com.api.educaia.models.TaskModel;
import com.api.educaia.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody @Valid  TaskDTO taskDTO) {
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        TaskModel taskModelResponse  = taskService.createTask(taskModel);

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
    public ResponseEntity<?> taskQuizFinished(@PathVariable("id") String id) {
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

    @GetMapping("/getRateQuestions")
    public ResponseEntity<List<RateQuestion>> getRoleQuestions()
    {
        List<RateQuestion> rateQuestions = new ArrayList<>();
        rateQuestions.add(new RateQuestion("'A tarefa foi clara e objetiva?'",0));
        rateQuestions.add(new RateQuestion("A tarefa foi desafiadora?",0));
        rateQuestions.add(new RateQuestion("A tarefa foi útil para o aprendizado?",0));
        rateQuestions.add(new RateQuestion("A tarefa foi adequada para o seu nível de conhecimento?",0));
        rateQuestions.add(new RateQuestion("A tarefa exigiu um tempo razoável para ser concluída?",0));
        rateQuestions.add(new RateQuestion("A tarefa permitiu que você aplicasse os conceitos aprendidos em sala de aula?",0));
        return ResponseEntity.ok(rateQuestions);
    }




}