package com.api.educaia.services;

import com.api.educaia.models.EventCalendarModel;

import java.util.List;
import java.util.UUID;

public interface CalendarService {
    void createEvent(EventCalendarModel eventCalendarModel);

    List<EventCalendarModel> listEvents();

    void deleteEvent(UUID eventId);



    List<EventCalendarModel> getEventsAdmin(String schoolId);

    List<EventCalendarModel> getEventsStudent( String schoolId, String classId, String userId);

    List<EventCalendarModel> getEventsByClassId(String classId);

    List<EventCalendarModel> getEventsTeacher(String userId, String schoolId);
}
