package com.api.educaia.repositories;

import com.api.educaia.models.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<ClassModel, UUID>{
}
