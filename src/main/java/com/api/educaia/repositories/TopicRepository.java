package com.api.educaia.repositories;

import com.api.educaia.models.TopicModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TopicRepository extends JpaRepository<TopicModel, UUID> {
    Optional<TopicModel> findById(UUID topicId);

}
