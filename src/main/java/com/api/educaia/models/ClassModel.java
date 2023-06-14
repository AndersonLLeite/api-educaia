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
@Table(name = "tb_class")
public class ClassModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String schoolId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SubjectModel> subjects;

    public void removeSubject(UUID subjectId) {
        this.subjects.removeIf(subjectModel -> subjectModel.getId().equals(subjectId));
    }

    public void addSubject(SubjectModel subjectModel) {
        this.subjects.add(subjectModel);
    }

    public double getAvg() {
        if (this.subjects.size() == 0) return 0;
        double sum = 0;
        for (SubjectModel subject : this.subjects) {
            sum += subject.getAvg();
        }

        return sum / this.subjects.size();
    }
}
