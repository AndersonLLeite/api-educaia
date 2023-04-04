package com.api.educaia.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Entity

@Table(name = "quizquestion")
public class QuizQuestionModel {
    public QuizQuestionModel() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(name = "question")
    private String question;

    @ElementCollection
    @CollectionTable(name = "question_answers", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "answer")
    private List<String> answers;

    @NotNull
    @Column(name = "correct_answer")
    private Integer correctAnswer;

    @NotNull
    @Column(name = "task_id")
    private Long taskId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setTaskId(Long id) {
        this.taskId = id;
    }
    public Long getTaskId() {
        return taskId;
    }
}