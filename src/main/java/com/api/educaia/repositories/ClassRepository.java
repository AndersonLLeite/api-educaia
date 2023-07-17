package com.api.educaia.repositories;

import com.api.educaia.models.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<ClassModel, UUID>{
    List<ClassModel> findAllBySchoolId(String schoolId);


}
