package com.api.educaia.repositories;

import com.api.educaia.models.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface QuizRepository extends JpaRepository<QuizModel, UUID> {
    Optional<QuizModel> findByTaskId(UUID taskId);
}
