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
    private String subject;
    private String teacherName;
    private String title;
    private String description;
    private Long deadLineDate;
    private Long creationDate;
    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<QuizQuestionModel> quizQuestions = new ArrayList<>();
    private boolean quizIsDone = false;
    private boolean rateIsDone = false;
    private String classId;
    private String schoolId;


}
