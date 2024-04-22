package com.api.educaia.repositories;

import com.api.educaia.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
   List<TaskModel> findByCreationDate(Long creationDate);
   Optional<TaskModel>  findById(String id);

   @Query("SELECT COUNT(t) FROM TaskModel t WHERE t.classId = :classId AND t.creationDate = :today")
   Long countTasksByClassIdAndCreationDate(@Param("classId") String classId, @Param("today") Long today);

    @Query("SELECT t FROM TaskModel t WHERE t.creationDate = :creationDate AND t.classId = :classId")
   List<TaskModel> findByCreationDateAndClassId(@Param("creationDate")
     Long creationDate, @Param("classId") String classId);
}
