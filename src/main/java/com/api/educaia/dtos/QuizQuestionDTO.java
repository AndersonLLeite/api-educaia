package com.api.educaia.dtos;

import java.util.List;


public class QuizQuestionDTO {

    private Long taskId;
    private String question;
    private List<String> answers;
    private int correctAnswer;

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

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public void setTaskId(Long id) {
        this.taskId = id;
    }
    public Long getTaskId() {
        return taskId;
    }
}