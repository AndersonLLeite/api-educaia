package com.api.educaia.dtos;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class QuizQuestionDTO {
    @NotNull
    private Long taskId;
    @NotBlank
    private String question;
    @NotNull
    private List<String> answers;
    @NotNull
    private int correctAnswer;
}