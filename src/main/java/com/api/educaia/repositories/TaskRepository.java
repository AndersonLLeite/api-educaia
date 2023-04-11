package com.api.educaia.repositories;

import com.api.educaia.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
   List<TaskModel> findByCreationDate(Long creationDate);
   Optional<TaskModel>  findById(Long id);
}
