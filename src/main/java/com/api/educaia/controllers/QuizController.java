package com.api.educaia.controllers;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.models.*;
import com.api.educaia.services.QuizService;
import com.api.educaia.services.TaskService;
import com.api.educaia.services.UserService;
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
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create-quiz-question", method = RequestMethod.POST)
    public ResponseEntity<?> createQuizQuestion(@RequestBody @Valid QuizQuestionDTO quizQuestionDTO) {
        var quizQuestion = new QuizQuestionModel();
        BeanUtils.copyProperties(quizQuestionDTO, quizQuestion);
        QuizQuestionModel quizQuestionModel = quizService.createQuizQuestion(quizQuestion);

        return new ResponseEntity<QuizQuestionModel>(quizQuestionModel, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/list-questions", method = RequestMethod.GET)
    public ResponseEntity<?> listQuizQuestions() {
        List<QuizQuestionModel> quizQuestions = quizService.listQuizQuestions();
        return new ResponseEntity<List<QuizQuestionModel>>(quizQuestions, HttpStatus.OK);
    }

    @GetMapping("/getQuizIsDone/{taskId}/{username}")
    public ResponseEntity<?> getQuizIsDone(@PathVariable UUID taskId, @PathVariable String username)
    {
        Optional<QuizModel> quizOp = quizService.getQuizByTaskId(taskId);
        if(!quizOp.isPresent())
        {
            return new ResponseEntity<String>("Quiz not found", HttpStatus.NOT_FOUND);
        }
        QuizModel quiz = quizOp.get();
        boolean quizISDone = quizService.getQuizIsDone(quiz, username);
        return ResponseEntity.ok(quizISDone);
    }

    @PutMapping("/updateQuizAddAnswerer/{taskId}/{username}")
    public ResponseEntity<?> updateQuizAddAnswerer(@PathVariable UUID taskId, @PathVariable String username, @RequestBody List<Integer> quizAnswers)
    {
        Optional<QuizModel> quizOp = quizService.getQuizByTaskId(taskId);
        if(!quizOp.isPresent())
        {
            return new ResponseEntity<String>("Quiz not found", HttpStatus.NOT_FOUND);
        }
        QuizModel quiz = quizOp.get();
        quizService.updateQuizAddAnswerer(quiz, username);

        Optional<TaskModel> taskOp = taskService.getTaskById(taskId);
        if(!taskOp.isPresent())
        {
            return new ResponseEntity<String>("Task not found", HttpStatus.NOT_FOUND);
        }
        TaskModel task = taskOp.get();
        int score = quizService.calculateQuizScoreAndAddHitToQuizQuestionIfHit(quiz, quizAnswers);
        int points = quizService.calculateQuizPoints(score);
        Optional<UserModel> userOp = userService.getUserByUsername(username);
        if(!userOp.isPresent())
        {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        UserModel user = userOp.get();
        userService.addPointsToUser(user, points);
        List<Integer> result = List.of(score, points);
        return ResponseEntity.ok(result);
    }

    //TODO apenas para testes retirar depois
    @GetMapping("/list-quiz")
    public ResponseEntity<?> listQuiz() {
        List<QuizModel> quiz = quizService.listQuiz();
        return new ResponseEntity<List<QuizModel>>(quiz, HttpStatus.OK);
    }
}
