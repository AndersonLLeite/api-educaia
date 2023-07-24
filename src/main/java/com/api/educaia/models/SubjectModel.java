package com.api.educaia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class SubjectModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String schoolId;
    private String classId;
    private String teacherId;
    private String teacherName;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluationModel> evaluations;

    public double getAvg() {
        if (this.evaluations.size() == 0) return 0;
        double sum = 0;
        for (EvaluationModel evaluation : this.evaluations) {
            sum += evaluation.getAvg();
        }
        return sum / this.evaluations.size();
    }

    public void addGradeToEvaluation(GradeModel gradeModel) {
        for (EvaluationModel evaluation : this.evaluations) {
            if (evaluation.getId().equals(UUID.fromString(gradeModel.getEvaluationId()))) {
                evaluation.add(gradeModel);
            }
        }
    }

    public void addEvaluation(EvaluationModel evaluationModel) {
        this.evaluations.add(evaluationModel);
    }

    public void deleteEvaluation(String evaluationId) {
        this.evaluations.removeIf(evaluation -> evaluation.getId().equals(UUID.fromString(evaluationId)));
    }
}