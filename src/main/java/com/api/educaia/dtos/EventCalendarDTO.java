package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventCalendarDTO {
    private UUID id;
    private String subject;
    private Long startTime;
    private Long endTime;
    private int backgroundColor;
    private boolean allDay;
    private String recurrenceRule;
    private List<Long> recurrenceExceptionDates;
    private String schoolId;
    private String classId;
    private String userId;


}
