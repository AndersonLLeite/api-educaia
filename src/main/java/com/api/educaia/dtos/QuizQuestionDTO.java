package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestionDTO {

    public QuizQuestionDTO(String question, List<String> answers, int hits, Integer correctAnswer, UUID taskId, int misses) {
        this.question = question;
        this.answers = answers;
        this.hits = hits;
        this.correctAnswer = correctAnswer;
        this.taskId = taskId;
        this.misses = misses;
    }
    private UUID id;
    @NotBlank
    private String question;
    @NotNull
    private List<String> answers;
    private int hits;
    @NotNull
    private int correctAnswer;
    private UUID taskId;
    private int misses;

}