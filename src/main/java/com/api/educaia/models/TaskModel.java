package com.api.educaia.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "task")
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "subject")
    private String subject;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline_date")
    private int deadLineDate;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<QuizQuestionModel> quizQuestions;


    @Column(name = "quiz_is_done")
    private boolean quizIsDone = false;

    @Column(name = "rate_is_done")
    private boolean rateIsDone = false;


    public void addQuizQuestion(QuizQuestionModel quizQuestion) {
        quizQuestion.setTaskId(this.id);
        this.quizQuestions.add(quizQuestion);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<QuizQuestionModel> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestionModel> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public boolean isQuizIsDone() {
        return quizIsDone;
    }

    public void setQuizIsDone(boolean quizIsDone) {
        this.quizIsDone = quizIsDone;
    }

    public boolean isRateIsDone() {
        return rateIsDone;
    }

    public void setRateIsDone(boolean rateIsDone) {
        this.rateIsDone = rateIsDone;
    }
}
