package com.api.educaia.services;

import com.api.educaia.dtos.TopicDTO;
import com.api.educaia.models.TopicAnswer;
import com.api.educaia.models.TopicModel;
import com.api.educaia.models.UserModel;
import com.api.educaia.repositories.TopicAnswerRepository;
import com.api.educaia.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserService userService;

    @Autowired
    TopicAnswerRepository topicAnswerRepository;
    @Override
    public TopicModel createTopic(TopicModel topicModel) {
        Optional<UserModel> userOp = userService.getUserByUsername(topicModel.getUsername());
        if (!userOp.isPresent()) {
            return null;
        }
        UserModel user = userOp.get();
        topicModel.setUserWhoCreated(user);
        return topicRepository.save(topicModel);
    }

    @Override
    public List<TopicModel> listTopics() {
        return topicRepository.findAll();
    }

    @Override
    public List<TopicModel> getRecentTopics() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "creationDate"));

        return topicRepository.findAll(pageable).getContent();
    }
    //TODO: Implement this method
    @Override
    public List<TopicModel> getPopularTopics() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "answers"));

        return topicRepository.findAll(pageable).getContent();
    }

    @Override
    public void addFavorite(UUID topicId, String username) {
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.addUserWhoFavorite(username);
    topicRepository.save(topic);
    }

    @Override
    public void removeFavorite(UUID topicId, String username) {
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.removeUserWhoFavorite(username);
        topicRepository.save(topic);
    }

    @Override
    public void removeTopic(UUID topicId) {
        topicRepository.deleteById(topicId);
    }

    @Override
    public TopicAnswer addAnswer(TopicAnswer topicAnswer, UUID topicId) {
        Optional<UserModel> userOp = userService.getUserByUsername(topicAnswer.getUserNameWhoAnswered());
        if (!userOp.isPresent()) {
            return null;
        }
        UserModel user = userOp.get();
        topicAnswer.setUserWhoAnswered(user);
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return null;
        }
        TopicModel topic = topicOp.get();
        topic.addAnswer(topicAnswer);
        topicRepository.save(topic);
        return topicAnswerRepository.save(topicAnswer) ;
    }

    @Override
    public List<TopicAnswer> listAnswers() {
        return topicAnswerRepository.findAll();
    }


}
