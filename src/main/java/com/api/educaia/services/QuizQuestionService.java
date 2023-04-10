package com.api.educaia.services;

import com.api.educaia.models.QuizQuestionModel;

import java.util.List;


public interface QuizQuestionService {
    public QuizQuestionModel createQuizQuestion(QuizQuestionModel quizQuestionModel);

    public List<QuizQuestionModel> listQuizQuestions();
}
