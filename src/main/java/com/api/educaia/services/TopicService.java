package com.api.educaia.services;

import com.api.educaia.dtos.TopicIdentifierDTO;
import com.api.educaia.models.TopicAnswer;
import com.api.educaia.models.TopicModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TopicService {
    TopicModel createTopic(TopicModel topicModel);

    List<TopicModel> listTopics();

    List<TopicIdentifierDTO> getRecentTopics();

    void removeTopic(UUID topicId);

    TopicAnswer addAnswer(TopicAnswer topicAnswer, UUID topicId);

    List<TopicAnswer> listAnswers();

    List<TopicIdentifierDTO> getPopularTopics();

    void addFavorite(UUID topicId, String username);

    void removeFavorite(UUID topicId, String username);

    List<TopicIdentifierDTO> getTopicByCategory(String category);

    void addLike(UUID topicId, String username);

    void removeLike(UUID topicId, String username);

    void openTopic(UUID topicId);

    void closeTopic(java.util.UUID topicId);

    void removeLikeToAnswer(UUID answerId, String username);

    void addLikeToAnswer(UUID answerId, String username);

    void removeAnswer(UUID answerId);

    void setBestAnswer(UUID answerId, UUID topicId);

    List<TopicIdentifierDTO> getUnauthorizedTopics();

    Optional<TopicModel> getTopicById(UUID topicId);

    void approveTopic(UUID topicId);
}
