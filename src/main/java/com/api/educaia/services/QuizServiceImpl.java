package com.api.educaia.services;

import com.api.educaia.models.QuizModel;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.TaskModel;
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
    private static final int QUIZ_POINT_MULTIPLIER = 10;
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

    @Override
    public int calculateQuizScoreAndAddHitToQuizQuestionIfHit(TaskModel task, List<Integer> quizAnswers) {
        List<Integer> correctAnswers = task.getCorrectAnswers();
        int score = 0;
        for (int i = 0; i < quizAnswers.size(); i++) {
            if(quizAnswers.get(i) != -1) {
                if (quizAnswers.get(i) == correctAnswers.get(i)) {
                    score++;
                    task.getQuizQuestions().get(i).addHit();
                }
            }
        }
        taskService.saveTask(task);
        return score;
    }

    @Override
    public int calculateQuizPoints(int score) {
        return score * QUIZ_POINT_MULTIPLIER;
    }
}
