package com.api.educaia.models;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class TaskModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String subject;
    private String teacherName;
    private String title;
    private String description;
    private Long deadLineDate;
    private Long creationDate;
    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<QuizQuestionModel> quizQuestions = new ArrayList<>();

    private String classId;
    private String schoolId;


    public List<Integer> getCorrectAnswers() {
        List<Integer> correctAnswers = new ArrayList<>();
        for (QuizQuestionModel quizQuestion : quizQuestions) {
            correctAnswers.add(quizQuestion.getCorrectAnswer());
        }
        return correctAnswers;
    }
}
