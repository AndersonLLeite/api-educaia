package com.api.educaia.repositories;

import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestionModel, UUID> {
}
