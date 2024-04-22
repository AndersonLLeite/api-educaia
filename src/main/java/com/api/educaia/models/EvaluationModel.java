package com.api.educaia.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_evaluation")
public class EvaluationModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String subjectId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GradeModel> grades;

    public void add(GradeModel gradeModel) {
        this.grades.add(gradeModel);
    }

    public double getAvg() {
        if (this.grades.size() == 0) return 0;
        double sum = 0;
        for (GradeModel grade : this.grades) {
            sum += grade.getGrade();
        }

        return sum / this.grades.size();
    }

    public void addGrade(GradeModel gradeModel) {
        this.grades.add(gradeModel);
    }
}
