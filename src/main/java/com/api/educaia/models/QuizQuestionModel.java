package com.api.educaia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_QUIZ_QUESTION")
public class QuizQuestionModel{
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    @Column(name = "question")
    private String question;

    @ElementCollection
    @CollectionTable(name = "question_answers", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "answer")
    private List<String> answers;
    private int hits = 0;
    private int misses = 0;

    @NotNull
    @Column(name = "correct_answer")
    private Integer correctAnswer;

    @NotNull
    @Column(name = "task_id")
    private UUID taskId;

    public QuizQuestionModel(String question, List<String> answers, Integer correctAnswer, UUID taskId) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.taskId = taskId;
    }

    public QuizQuestionModel(String question, List<String> answers, int correctAnswer, UUID taskId) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.taskId = taskId;

    }

    public void addHit() {
        this.hits++;
    }

    public void addMiss(){this.misses++;}

}