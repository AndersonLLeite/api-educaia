package com.api.educaia.services;

import com.api.educaia.dtos.TopicDTO;
import com.api.educaia.models.TopicAnswer;
import com.api.educaia.models.TopicModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TopicService {
    TopicModel createTopic(TopicModel topicModel);

    List<TopicModel> listTopics();

    List<TopicModel> getRecentTopics();

    void removeTopic(UUID topicId);

    TopicAnswer addAnswer(TopicAnswer topicAnswer, UUID topicId);

    List<TopicAnswer> listAnswers();

    List<TopicModel> getPopularTopics();

    void addFavorite(UUID topicId, String username);

    void removeFavorite(UUID topicId, String username);
}
