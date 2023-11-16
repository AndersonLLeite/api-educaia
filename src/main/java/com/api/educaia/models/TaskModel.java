package com.api.educaia.models;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class TaskModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String SubjectName;
    private String title;
    private String description;
    private Long deadLineDate;
    private Long creationDate;
    private String teacherName;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<QuizQuestionModel> quizQuestions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectModel subject;
    private String classId;
    private String schoolId;


    @JsonIgnore
    public List<Integer> getCorrectAnswers() {
        List<Integer> correctAnswers = new ArrayList<>();
        for (QuizQuestionModel quizQuestion : quizQuestions) {
            correctAnswers.add(quizQuestion.getCorrectAnswer());
        }
        return correctAnswers;
    }
}
