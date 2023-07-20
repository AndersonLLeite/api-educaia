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
}
