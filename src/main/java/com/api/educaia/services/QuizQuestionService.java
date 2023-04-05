package com.api.educaia.services;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.repositories.QuizQuestionRepository;
import com.api.educaia.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuizQuestionService {
    public QuizQuestionModel criateQuizQuestion(QuizQuestionModel quizQuestionModel);

    public List<QuizQuestionModel> listQuizQuestions();
}
