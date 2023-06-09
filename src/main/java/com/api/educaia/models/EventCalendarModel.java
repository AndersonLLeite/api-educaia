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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class EventCalendarModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String subject;
    private Long startTime;
    private Long endTime;
    private int color;
    private boolean allDay;
    private String recurrenceRule;
    @ElementCollection
    private List<Long> recurrenceExceptionDates;
    private String schoolId;
    private String classId;
    private String userId;

    public void addExceptionDate(Long exceptionDate) {
        this.recurrenceExceptionDates.add(exceptionDate);
    }
}
