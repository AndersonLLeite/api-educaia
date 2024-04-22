package com.api.educaia.services;

import com.api.educaia.dtos.QuizDTO;
import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.TaskModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    public TaskModel createTask(TaskModel taskModel);
    public List<TaskModel> listTasks();

    List<TaskModel> getTasksByCreationDateAndClassId(Long creationDate, String classId);

    Optional<TaskModel> getTaskById(UUID id);

    TaskModel saveTask(TaskModel taskModel);

    Long countTasksByClassIdAndCreationDate(String classId, long todayMillis);

    void createRateResponse(UUID taskID);


    QuizDTO getQuiz(TaskModel task);

    QuizDTO createQuiz(QuizDTO quizDTO, TaskModel task);

    QuizQuestionDTO addQuestion(TaskModel task, QuizQuestionDTO quizQuestionDTO);

    List<TaskDTO> convertTaskModelToTaskDTO(List<TaskModel> tasks);
}