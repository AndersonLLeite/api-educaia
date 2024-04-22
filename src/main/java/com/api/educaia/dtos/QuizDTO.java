package com.api.educaia.dtos;

import com.api.educaia.models.QuizQuestionModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class QuizDTO {
    private UUID id;
    private UUID taskId;
    private List<String> studentsWhoAnswered;
    private List<QuizQuestionModel> quizQuestions;
    private String description;

    public QuizDTO(UUID id, UUID taskId, List<String> studentsWhoAnswered, List<QuizQuestionModel> quizQuestions, String description) {
        this.id = id;
        this.taskId = taskId;
        this.studentsWhoAnswered = studentsWhoAnswered;
        this.quizQuestions = quizQuestions;
        this.description = description;
    }



}
