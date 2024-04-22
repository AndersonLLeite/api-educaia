package com.api.educaia.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "grade")
public class GradeModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String evaluationId;
//    private String subjectId;
//    private String schoolId;
//    private String classId;
    private String userId;
    private double grade;
    private String Status;


}
