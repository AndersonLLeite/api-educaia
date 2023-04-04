package com.api.educaia.controllers;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.services.QuizquestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/educaia")
public class QuizQuestionController {
    @Autowired
    private QuizquestionService quizQuestionService;

    @RequestMapping(value = "/quiz-question/", method = RequestMethod.POST)
    public ResponseEntity<?> criarQuizQuestion(@RequestBody QuizQuestionDTO quizQuestionDTO) {
        var quizQuestion = new QuizQuestionModel();
        BeanUtils.copyProperties(quizQuestionDTO, quizQuestion);
        QuizQuestionModel quizQuestionModel = quizQuestionService.criaQuizQuestion(quizQuestion);

        return new ResponseEntity<QuizQuestionModel>(quizQuestionModel, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/listar-questions}", method = RequestMethod.GET)
    public ResponseEntity<?> listarQuizQuestions() {
        List<QuizQuestionModel> quizQuestions = quizQuestionService.listarQuizQuestions();
        return new ResponseEntity<List<QuizQuestionModel>>(quizQuestions, HttpStatus.OK);
    }
}
