package com.api.educaia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subject")
    private String subject;
    @Column(name = "teacher_name", nullable = false)
    private String teacherName;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "deadline_date")
    private Long deadLineDate;
    @Column(name = "creation_date", nullable = false)
    private Long creationDate;
    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<QuizQuestionModel> quizQuestions = new ArrayList<>();
    @Column(name = "quiz_is_done")
    private boolean quizIsDone = false;
    @Column(name = "rate_is_done")
    private boolean rateIsDone = false;


}
