package com.api.educaia.repositories;

import com.api.educaia.models.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, UUID> {
    List<SubjectModel> findByClassId(String classId);

    @Query("SELECT s FROM SubjectModel s WHERE s.classId = :classId AND s.teacherId = :teacherId")
    List<SubjectModel> findByClassIdAndTeacherId(String classId, String teacherId);
}
