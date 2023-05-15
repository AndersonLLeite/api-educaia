package com.api.educaia.repositories;

import com.api.educaia.models.TopicAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TopicAnswerRepository extends JpaRepository<TopicAnswer, UUID> {
}
