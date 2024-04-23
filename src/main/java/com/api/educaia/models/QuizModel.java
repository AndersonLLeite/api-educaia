package com.api.educaia.models;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz")
public class QuizModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID taskId;
    @ElementCollection
    private List<String> studentsWhoAnswered = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizQuestionModel> quizQuestions = new ArrayList<>();
    private String description;
//    @OneToOne
//    @JoinColumn(name = "task_id")
//    private TaskModel task;

    public QuizModel(UUID taskID, String description) {
        this.description = description;
        this.taskId = taskID;
    }


    public void addQuestion(QuizQuestionDTO quizQuestionDTO) {
        QuizQuestionModel quizQuestionModel = new QuizQuestionModel(quizQuestionDTO.getQuestion(), quizQuestionDTO.getAnswers(), quizQuestionDTO.getCorrectAnswer(), quizQuestionDTO.getTaskId());
        this.quizQuestions.add(quizQuestionModel);
    }

        @JsonIgnore
    public List<Integer> getCorrectAnswers() {
        List<Integer> correctAnswers = new ArrayList<>();
        for (QuizQuestionModel quizQuestion : quizQuestions) {
            correctAnswers.add(quizQuestion.getCorrectAnswer());
        }
        return correctAnswers;
    }
}
