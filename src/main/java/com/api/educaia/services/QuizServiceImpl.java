package com.api.educaia.services;

import com.api.educaia.models.QuizModel;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.repositories.QuizQuestionRepository;
import com.api.educaia.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private TaskService taskService;
    @Transactional
    public QuizQuestionModel createQuizQuestion(QuizQuestionModel quizQuestionModel) {
        return quizQuestionRepository.save(quizQuestionModel);
    }

    public List<QuizQuestionModel> listQuizQuestions() {
        return quizQuestionRepository.findAll();
    }

    @Override
    public void createQuiz(UUID taskId) {
        QuizModel quizModel = new QuizModel(taskId);
        quizRepository.save(quizModel);
    }

    @Override
    public Optional<QuizModel> getQuizByTaskId(UUID taskId) {
        return quizRepository.findByTaskId(taskId);
    }

    @Override
    public boolean getQuizIsDone(QuizModel quiz, String username) {
        return quiz.getStudentsWhoAnswered().contains(username);
    }

    @Override
    public void updateQuizAddAnswerer(QuizModel quiz, String username) {
        quiz.getStudentsWhoAnswered().add(username);
        quizRepository.save(quiz);
    }

    @Override
    public List<QuizModel> listQuiz() {
        return quizRepository.findAll();
    }
}
