package com.api.educaia.services;

import com.api.educaia.dtos.TaskDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.repositories.QuizQuestionRepository;
import com.api.educaia.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    public TaskModel createTask(TaskModel taskModel);
    public List<TaskModel> listTasks();

    List<TaskModel> getTasksByCreationDate(Long creationDate);

    Optional<TaskModel> getTaskById(String id);

    TaskModel saveTask(TaskModel taskModel);

    Long countTasksByClassIdAndCreationDate(String classId, long todayMillis);
}