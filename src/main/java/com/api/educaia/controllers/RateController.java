package com.api.educaia.controllers;

import com.api.educaia.models.RateModel;
import com.api.educaia.models.RateQuestionModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.models.UserModel;
import com.api.educaia.services.RateService;
import com.api.educaia.services.TaskService;
import com.api.educaia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private TaskService taskService;

    @Autowired
    UserService userService;

    @PutMapping("/updateRateResponse/{taskId}/{username}")
    public ResponseEntity<?> updateRateResponse(@PathVariable UUID taskId, @PathVariable String username, @RequestBody List<Integer> rateAnswers)
    {
        Optional<RateModel> rateResponseOp = rateService.getRateResponseByTaskId(taskId);
        if(!rateResponseOp.isPresent())
        {
            return new ResponseEntity<String>("RateResponse not found", HttpStatus.NOT_FOUND);
        }
        RateModel rateResponse = rateResponseOp.get();
        rateService.updateRateModel(rateResponse, rateAnswers, username);

        Optional<UserModel> userOp = userService.getUserByUsername(username);
        if(!userOp.isPresent())
        {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        UserModel user = userOp.get();
        userService.addPointsToUser(user, 10);
        return ResponseEntity.ok(rateResponse);


    }
    @GetMapping("/getRateResponseByTaskId/{taskId}")
    public ResponseEntity<?> getRateResponseByTaskId(@PathVariable UUID taskId)
    {
        Optional<RateModel> rateResponseOp = rateService.getRateResponseByTaskId(taskId);
        if(!rateResponseOp.isPresent())
        {
            return new ResponseEntity<String>("RateResponse not found", HttpStatus.NOT_FOUND);
        }
        RateModel rateResponse = rateResponseOp.get();
        return ResponseEntity.ok(rateResponse);
    }


    @GetMapping("/getRateQuestions")
    public ResponseEntity<List<RateQuestionModel>> getRateQuestions()
    {
        List<RateQuestionModel> rateQuestions = new ArrayList<>();
        rateQuestions.add(new RateQuestionModel("'A tarefa foi clara e objetiva?'",0));
        rateQuestions.add(new RateQuestionModel("A tarefa foi útil para o aprendizado?",0));
        rateQuestions.add(new RateQuestionModel("A tarefa foi adequada para o seu nível de conhecimento?",0));
        rateQuestions.add(new RateQuestionModel("A tarefa exigiu um tempo razoável para ser concluída?",0));
        rateQuestions.add(new RateQuestionModel("A tarefa permitiu que você aplicasse os conceitos aprendidos em sala de aula?",0));
        return ResponseEntity.ok(rateQuestions);
    }

    @GetMapping("/getRateIsDone/{taskId}/{username}")
    public ResponseEntity<?> getRateIsDone(@PathVariable UUID taskId, @PathVariable String username)
    {
        Optional<RateModel> rateResponseOp = rateService.getRateResponseByTaskId(taskId);
        if(!rateResponseOp.isPresent())
        {
            return new ResponseEntity<String>("RateResponse not found", HttpStatus.NOT_FOUND);
        }
        RateModel rateResponse = rateResponseOp.get();
        boolean rateISDone = rateService.getRateIsDone(rateResponse, username);
        return ResponseEntity.ok(rateISDone);
    }
}
