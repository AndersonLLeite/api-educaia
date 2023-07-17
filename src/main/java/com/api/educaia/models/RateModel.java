package com.api.educaia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID taskId;
    @ElementCollection
    private List<String> studentsWhoAnswered;

    private int[] question1 = new int[5];
    private int[] question2 = new int[5];
    private int[] question3 = new int[5];
    private int[] question4 = new int[5];
    private int[] question5 = new int[5];

    public RateModel(UUID taskID) {
        this.taskId = taskID;
    }

    public void updateQuestion1(int option) {
        this.question1[option]++;
    }

    public void updateQuestion2(int option) {
        this.question2[option]++;
    }

    public void updateQuestion3(int option) {
        this.question3[option]++;
    }

    public void updateQuestion4(int option) {
        this.question4[option]++;
    }

    public void updateQuestion5(int option) {
        this.question5[option]++;
    }

    public void setRateQuestionsAddVotes(List<Integer> rateQuestions) {
        this.question1[rateQuestions.get(0)] += 1;
        this.question2[rateQuestions.get(1)] += 1;
        this.question3[rateQuestions.get(2)] += 1;
        this.question4[rateQuestions.get(3)] += 1;
        this.question5[rateQuestions.get(4)] += 1;
    }

    public void addStudentWhoAnswered(String username) {
        this.studentsWhoAnswered.add(username);
    }
}
