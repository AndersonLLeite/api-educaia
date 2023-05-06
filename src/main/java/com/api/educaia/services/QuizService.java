package com.api.educaia.services;

import com.api.educaia.models.QuizModel;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.TaskModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface QuizService {
    public QuizQuestionModel createQuizQuestion(QuizQuestionModel quizQuestionModel);

    public List<QuizQuestionModel> listQuizQuestions();

    void createQuiz(UUID taskId);

    Optional<QuizModel> getQuizByTaskId(UUID taskId);

    boolean getQuizIsDone(QuizModel quiz, String username);

    void updateQuizAddAnswerer(QuizModel quiz, String username);

    List<QuizModel> listQuiz();

    int calculateQuizScoreAndAddHitToQuizQuestionIfHit(TaskModel task, List<Integer> quizAnswers);

    int calculateQuizPoints(int score);
}
