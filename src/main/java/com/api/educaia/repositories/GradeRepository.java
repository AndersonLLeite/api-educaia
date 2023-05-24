package com.api.educaia.repositories;

import com.api.educaia.models.GradeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GradeRepository extends JpaRepository<GradeModel, UUID> {
}
