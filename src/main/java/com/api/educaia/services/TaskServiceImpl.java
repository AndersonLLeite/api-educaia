package com.api.educaia.services;

import com.api.educaia.models.RateModel;
import com.api.educaia.models.TaskModel;
import com.api.educaia.repositories.QuizQuestionRepository;
import com.api.educaia.repositories.RateRepository;
import com.api.educaia.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<TaskModel> getTasksByCreationDate(Long creationDate) {
        return taskRepository.findByCreationDate(creationDate);
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



}
