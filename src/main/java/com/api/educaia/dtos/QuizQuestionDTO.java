package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionDTO {

    public QuizQuestionDTO(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
    @NotNull
    private UUID taskId;
    @NotBlank
    private String question;
    @NotNull
    private List<String> answers;
    @NotNull
    private int correctAnswer;

}