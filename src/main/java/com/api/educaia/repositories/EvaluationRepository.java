package com.api.educaia.repositories;

import com.api.educaia.models.ClassModel;
import com.api.educaia.models.EvaluationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationModel, UUID> {
}
