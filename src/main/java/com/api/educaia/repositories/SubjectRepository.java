package com.api.educaia.repositories;

import com.api.educaia.models.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, UUID> {
    List<SubjectModel> findByClassId(String classId);
}
