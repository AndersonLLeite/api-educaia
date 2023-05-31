package com.api.educaia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
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
    private String eventName;
    private Long initialDateAndHour;
    private Long endDateAndHour;
    private int backgroundColor;
    private boolean allDay;
    private boolean recurringWeekly;
    private String schoolId;
    private String classId;
    private String userId;
}
