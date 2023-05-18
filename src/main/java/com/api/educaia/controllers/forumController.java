package com.api.educaia.controllers;

import com.api.educaia.dtos.TopicAnswerDTO;
import com.api.educaia.dtos.TopicDTO;
import com.api.educaia.models.TopicAnswer;
import com.api.educaia.models.TopicModel;
import com.api.educaia.services.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/forum")
public class forumController {
    @Autowired
    TopicService topicService;

    @PostMapping("/create-topic")
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicDTO topicDTO){
        var topicModel = new TopicModel();
        BeanUtils.copyProperties(topicDTO, topicModel);
        TopicModel topicModelResponse = topicService.createTopic(topicModel);
        return ResponseEntity.ok(topicModelResponse);
    }

    @GetMapping("/list-topics")
    public ResponseEntity<?> listTopics(){
        return ResponseEntity.ok(topicService.listTopics());
    }

    @GetMapping("/get-recent-topics")
    public ResponseEntity<?> getRecentTopics(){
        return ResponseEntity.ok(topicService.getRecentTopics());
    }
    @GetMapping("/get-popular-topics")
    public ResponseEntity<?> getPopularTopics(){
        return ResponseEntity.ok(topicService.getPopularTopics());
    }

    @GetMapping("/get-topics-by-category")
    public ResponseEntity<?> getTopicByCategory(@RequestParam("category") String category){
        return ResponseEntity.ok(topicService.getTopicByCategory(category));
    }

    @DeleteMapping("/remove-topic/{topicId}")
    public ResponseEntity<?> removeTopic(@PathVariable UUID topicId){

        topicService.removeTopic(topicId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-answer/{topicId}")
    public ResponseEntity<?> addAnswer(@PathVariable UUID topicId, @RequestBody @Valid TopicAnswerDTO topicAnswerDTO){
        TopicAnswer topicAnswer = new TopicAnswer();
        BeanUtils.copyProperties(topicAnswerDTO, topicAnswer);
        topicAnswer.setTopicId(topicId);
        return ResponseEntity.ok(topicService.addAnswer(topicAnswer, topicId));
    }
    //TODO: para testes
    @GetMapping("/list-answers")
    public ResponseEntity<?> listAnswers(){
        return ResponseEntity.ok(topicService.listAnswers());
    }

    @PostMapping("/add-favorite/{topicId}/{username}")
    public ResponseEntity<?> addFavorite(@PathVariable UUID topicId, @PathVariable String username){
        topicService.addFavorite(topicId, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-favorite/{topicId}/{username}")
    public ResponseEntity<?> removeFavorite(@PathVariable UUID topicId, @PathVariable String username){
        topicService.removeFavorite(topicId, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-like/{topicId}/{username}")
    public ResponseEntity<?> addLike(@PathVariable UUID topicId, @PathVariable String username){
        topicService.addLike(topicId, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-like/{topicId}/{username}")
    public ResponseEntity<?> removeLike(@PathVariable UUID topicId, @PathVariable String username){
        topicService.removeLike(topicId, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/open-topic/{topicId}")
    public ResponseEntity<?> openTopic(@PathVariable UUID topicId){
        topicService.openTopic(topicId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/close-topic/{topicId}")
    public ResponseEntity<?> closeTopic(@PathVariable UUID topicId){
        topicService.closeTopic(topicId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remove-like-to-answer/{answerId}/{username}")
    public ResponseEntity<?> removeLikeToAnswer(@PathVariable UUID answerId, @PathVariable String username){
        topicService.removeLikeToAnswer(answerId, username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add-like-to-answer/{answerId}/{username}")
    public ResponseEntity<?> addLikeToAnswer(@PathVariable UUID answerId, @PathVariable String username){
        topicService.addLikeToAnswer(answerId, username);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove-answer/{answerId}")
    public ResponseEntity<?> removeAnswer(@PathVariable UUID answerId){
        topicService.removeAnswer(answerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/set-best-answer/{answerId}/{topicId}")
    public ResponseEntity<?> setBestAnswer(@PathVariable UUID answerId, @PathVariable UUID topicId){
        topicService.setBestAnswer(answerId, topicId);
        return ResponseEntity.ok().build();
    }







}
