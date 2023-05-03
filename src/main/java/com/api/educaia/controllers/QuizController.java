package com.api.educaia.controllers;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.models.QuizModel;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.RateModel;
import com.api.educaia.services.QuizService;
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
    public ResponseEntity<?> updateQuizAddAnswerer(@PathVariable UUID taskId, @PathVariable String username)
    {
        Optional<QuizModel> quizOp = quizService.getQuizByTaskId(taskId);
        if(!quizOp.isPresent())
        {
            return new ResponseEntity<String>("Quiz not found", HttpStatus.NOT_FOUND);
        }
        QuizModel quiz = quizOp.get();
        quizService.updateQuizAddAnswerer(quiz, username);
        return ResponseEntity.ok(quiz);
    }

    //TODO apenas para testes retirar depois
    @GetMapping("/list-quiz")
    public ResponseEntity<?> listQuiz() {
        List<QuizModel> quiz = quizService.listQuiz();
        return new ResponseEntity<List<QuizModel>>(quiz, HttpStatus.OK);
    }
}
