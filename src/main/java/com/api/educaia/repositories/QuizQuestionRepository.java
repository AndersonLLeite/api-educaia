package com.api.educaia.repositories;

import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.models.RateQuestionModel;
import com.api.educaia.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestionModel, UUID> {
}
