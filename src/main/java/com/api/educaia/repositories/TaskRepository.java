package com.api.educaia.repositories;

import com.api.educaia.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<TaskModel, Long> {
}
