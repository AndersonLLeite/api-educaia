package com.api.educaia.services;

import com.api.educaia.dtos.QuizDTO;
import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.QuizModel;
import com.api.educaia.models.RateModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.repositories.QuizQuestionRepository;
import com.api.educaia.repositories.RateRepository;
import com.api.educaia.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RateRepository rateResponseRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Transactional
    public TaskModel createTask(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }
    public List<TaskModel> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskModel> getTasksByCreationDateAndClassId(Long creationDate, String classId) {
        return taskRepository.findByCreationDateAndClassId(creationDate, classId);

    }

    @Override
    public Optional<TaskModel> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    @Override
    public TaskModel saveTask(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    @Override
    public Long countTasksByClassIdAndCreationDate(String classId, long todayMillis) {
        return taskRepository.countTasksByClassIdAndCreationDate(classId, todayMillis);
    }
    @Transactional
    @Override
    public void createRateResponse(UUID taskID) {
        RateModel rateResponseModel = new RateModel(taskID);
        rateResponseRepository.save(rateResponseModel);
    }

    @Override
    public QuizDTO getQuiz(TaskModel task) {
        QuizModel quiz = task.getQuiz();
        if (quiz == null) {
            return null;
        }


        return new QuizDTO(quiz.getId(), quiz.getTaskId(), quiz.getStudentsWhoAnswered(), quiz.getQuizQuestions(), quiz.getDescription());


    }

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO, TaskModel task){
        QuizModel quiz = new QuizModel(quizDTO.getTaskId(), quizDTO.getDescription());
        task.setQuiz(quiz);
        taskRepository.save(task);
        return new QuizDTO(task.getQuiz().getId(), quiz.getTaskId(), quiz.getStudentsWhoAnswered(), quiz.getQuizQuestions(), quiz.getDescription());
    }

    @Override
    public QuizQuestionDTO addQuestion(TaskModel task, QuizQuestionDTO quizQuestionDTO) {
        QuizModel quiz = task.getQuiz();
        quiz.addQuestion(quizQuestionDTO);
        taskRepository.save(task);
        QuizQuestionDTO quizQuestionDTOResponse = new QuizQuestionDTO(task.getQuiz().getQuizQuestions().get(task.getQuiz().getQuizQuestions().size() - 1).getId(), quizQuestionDTO.getQuestion(), quizQuestionDTO.getAnswers(), quizQuestionDTO.getHits(), quizQuestionDTO.getCorrectAnswer(), quizQuestionDTO.getTaskId(), quizQuestionDTO.getMisses());
        return quizQuestionDTOResponse;
    }

    @Override
    public List<TaskDTO> convertTaskModelToTaskDTO(List<TaskModel> tasks) {
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (TaskModel task : tasks) {
            taskDTOS.add(new TaskDTO(task.getId(), task.getSubjectName(), task.getTeacherName(), task.getTitle(), task.getDescription(), task.getDeadLineDate(), task.getCreationDate(), task.getClassId(), task.getSchoolId()));
        }
        return taskDTOS;

    }


}
