package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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