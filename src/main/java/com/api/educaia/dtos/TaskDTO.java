package com.api.educaia.dtos;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

public class TaskDTO  {
    private String subject;
    private String teacherName;
    private String title;
    private String description;
    private int deadLineDate;
//    private List<QuizQuestionDTO> quizQuestions;
//
//    private boolean quizIsDone;
//    private boolean rateIsDone;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(int deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

//    public List<QuizQuestionDTO> getQuizQuestions() {
//        return quizQuestions;
//    }
//
//    public void setQuizQuestions(List<QuizQuestionDTO> quizQuestions) {
//        this.quizQuestions = quizQuestions;
//    }
//
//    public boolean isQuizIsDone() {
//        return quizIsDone;
//    }
//
//    public void setQuizIsDone(boolean quizIsDone) {
//        this.quizIsDone = quizIsDone;
//    }
//
//    public boolean isRateIsDone() {
//        return rateIsDone;
//    }
//
//    public void setRateIsDone(boolean rateIsDone) {
//        this.rateIsDone = rateIsDone;
//    }
}