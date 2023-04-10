package com.api.educaia.controllers;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.services.QuizQuestionService;
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
public class QuizQuestionController {
    @Autowired
    private QuizQuestionService quizQuestionService;

    @RequestMapping(value = "/create-quiz-question", method = RequestMethod.POST)
    public ResponseEntity<?> createQuizQuestion(@RequestBody @Valid QuizQuestionDTO quizQuestionDTO) {
        var quizQuestion = new QuizQuestionModel();
        BeanUtils.copyProperties(quizQuestionDTO, quizQuestion);
        QuizQuestionModel quizQuestionModel = quizQuestionService.createQuizQuestion(quizQuestion);

        return new ResponseEntity<QuizQuestionModel>(quizQuestionModel, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/list-questions", method = RequestMethod.GET)
    public ResponseEntity<?> listQuizQuestions() {
        List<QuizQuestionModel> quizQuestions = quizQuestionService.listQuizQuestions();
        return new ResponseEntity<List<QuizQuestionModel>>(quizQuestions, HttpStatus.OK);
    }
}
