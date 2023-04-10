package com.api.educaia.services;

import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.repositories.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuizQuestionServiceImpl implements QuizQuestionService{
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private TaskService taskService;
    @Transactional
    public QuizQuestionModel createQuizQuestion(QuizQuestionModel quizQuestionModel) {
        return quizQuestionRepository.save(quizQuestionModel);
    }

    public List<QuizQuestionModel> listQuizQuestions() {
        return quizQuestionRepository.findAll();
    }
}
