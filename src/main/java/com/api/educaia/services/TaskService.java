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

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public void createTask() {

    }

    public TaskModel save(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public List<TaskModel> listarTasks() {
        return taskRepository.findAll();
    }

}