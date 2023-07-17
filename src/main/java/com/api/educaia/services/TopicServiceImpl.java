package com.api.educaia.services;

import com.api.educaia.dtos.TopicIdentifierDTO;
import com.api.educaia.models.TopicAnswer;
import com.api.educaia.models.TopicModel;
import com.api.educaia.models.UserModel;
import com.api.educaia.repositories.TopicAnswerRepository;
import com.api.educaia.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<TopicIdentifierDTO> getRecentTopics() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "creationDate"));
        List<TopicModel> topics = topicRepository.findAll(pageable).getContent();
        List<TopicIdentifierDTO> topicsIdentifiers = new ArrayList<>();
        for (TopicModel topic: topics
        ) {
            if (topic.getIsUnauthorized()) {
                continue;
            }
            TopicIdentifierDTO topicIdentifier = new TopicIdentifierDTO(topic.getId(), topic.getTitle(), topic.getCategory(), topic.getUserWhoCreated().getNameComplete(), topic.getAnswers().size(), topic.getUsernamesWhoFavorite().size());
            topicsIdentifiers.add(topicIdentifier);        }
        return topicsIdentifiers;
    }
    //TODO: Implement this method
    @Override
    public List<TopicIdentifierDTO> getPopularTopics() {
        Pageable top3FavoriteTopic = PageRequest.of(0, 3);
        List<TopicModel> topics = topicRepository.findTop3TopicsByFavorites(top3FavoriteTopic);
        List<TopicIdentifierDTO> topicsIdentifiers = new ArrayList<>();
        for (TopicModel topic: topics
        ) {
            if (topic.getIsUnauthorized()) {
                continue;
            }
            TopicIdentifierDTO topicIdentifier = new TopicIdentifierDTO(topic.getId(), topic.getTitle(), topic.getCategory(), topic.getUserWhoCreated().getNameComplete(), topic.getAnswers().size(), topic.getUsernamesWhoFavorite().size());
            topicsIdentifiers.add(topicIdentifier);        }

        return  topicsIdentifiers;
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
    public List<TopicIdentifierDTO> getTopicByCategory(String category) {
        List<TopicModel> topicsByCategory = topicRepository.findByCategory(category);
        List<TopicIdentifierDTO> topicsIdentifiers = new ArrayList<>();
        for (TopicModel topic: topicsByCategory
             ) {
            if (topic.getIsUnauthorized()) {
                continue;
            }
            TopicIdentifierDTO topicIdentifier = new TopicIdentifierDTO(topic.getId(), topic.getTitle(), topic.getCategory(), topic.getUserWhoCreated().getNameComplete(), topic.getAnswers().size(), topic.getUsernamesWhoFavorite().size());
            topicsIdentifiers.add(topicIdentifier);        }
        return topicsIdentifiers;
    }

    @Override
    public void addLike(UUID topicId, String username) {
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.addUserWhoLiked(username);
        topicRepository.save(topic);
    }

    @Override
    public void removeLike(UUID topicId, String username) {
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.removeUserWhoLiked(username);
        topicRepository.save(topic);
    }

    @Override
    public void openTopic(UUID topicId) {
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.setOpen(true);
        topicRepository.save(topic);
    }

    @Override
    public void closeTopic(UUID topicId) {
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.setOpen(false);
        topicRepository.save(topic);
    }

    @Override
    public void removeLikeToAnswer(UUID answerId, String username) {
        Optional<TopicAnswer> answerOp = topicAnswerRepository.findById(answerId);
        if (!answerOp.isPresent()) {
            return;
        }
        TopicAnswer answer = answerOp.get();
        answer.removeUserWhoLiked(username);
        topicAnswerRepository.save(answer);
    }

    @Override
    public void addLikeToAnswer(UUID answerId, String username) {
        Optional<TopicAnswer> answerOp = topicAnswerRepository.findById(answerId);
        if (!answerOp.isPresent()) {
            return;
        }
        TopicAnswer answer = answerOp.get();
        answer.addUserWhoLiked(username);
        topicAnswerRepository.save(answer);

    }

    @Override
    public void removeAnswer(UUID answerId) {
        Optional<TopicAnswer> answerOp = topicAnswerRepository.findById(answerId);
        if (!answerOp.isPresent()) {
            return;
        }
        TopicAnswer answer = answerOp.get();
        topicAnswerRepository.delete(answer);
    }

    @Override
    public void setBestAnswer(UUID answerId, UUID topicId) {
        Optional<TopicAnswer> answerOp = topicAnswerRepository.findById(answerId);
        if (!answerOp.isPresent()) {
            return;
        }
        TopicAnswer answer = answerOp.get();
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.setBestAnswer(answer);
        topicRepository.save(topic);
    }

    @Override
    public List<TopicIdentifierDTO> getUnauthorizedTopics() {
        List<TopicModel> closedTopics = topicRepository.findByUnauthorized(true);
        List<TopicIdentifierDTO> topics = new ArrayList<>();
        for(TopicModel closed: closedTopics ){
            TopicIdentifierDTO topicIdentifier = new TopicIdentifierDTO(closed.getId(), closed.getTitle(), closed.getCategory(), closed.getUserWhoCreated().getNameComplete(), closed.getAnswers().size(), closed.getUsernamesWhoFavorite().size());
            topics.add(topicIdentifier);
        }

        return topics;
    }

    @Override
    public Optional<TopicModel> getTopicById(UUID topicId) {
        return topicRepository.findById(topicId);
    }

    @Override
    public void approveTopic(UUID topicId) {
        //TODO: notificar ao usuário que o tópico foi aprovado
        Optional<TopicModel> topicOp = topicRepository.findById(topicId);
        if (!topicOp.isPresent()) {
            return;
        }
        TopicModel topic = topicOp.get();
        topic.setUnauthorized(false);
        topicRepository.save(topic);
    }


    @Override
    public void  removeTopic(UUID topicId) {
        Optional<TopicModel> topicModel = topicRepository.findById(topicId);
        if(!topicModel.isPresent()){
            return;
        }
        TopicModel topic = topicModel.get();
        topicRepository.delete(topic);
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
