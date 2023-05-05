package com.api.educaia.services;

import com.api.educaia.models.RateModel;
import com.api.educaia.models.TaskModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    public TaskModel createTask(TaskModel taskModel);
    public List<TaskModel> listTasks();

    List<TaskModel> getTasksByCreationDate(Long creationDate);

    Optional<TaskModel> getTaskById(UUID id);

    TaskModel saveTask(TaskModel taskModel);

    Long countTasksByClassIdAndCreationDate(String classId, long todayMillis);

    void createRateResponse(UUID taskID);


}