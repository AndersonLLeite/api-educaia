package com.api.educaia.services;

import com.api.educaia.models.TaskModel;
import com.api.educaia.repositories.QuizQuestionRepository;
import com.api.educaia.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public TaskModel createTask(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }
    public List<TaskModel> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskModel> getTasksByCreationDate(Long creationDate) {
        return taskRepository.findByCreationDate(creationDate);
    }

}
