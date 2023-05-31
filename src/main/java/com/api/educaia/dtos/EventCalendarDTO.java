package com.api.educaia.dtos;

import com.api.educaia.models.EventCalendarModel;
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
