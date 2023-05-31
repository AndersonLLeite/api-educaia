package com.api.educaia.services;

import com.api.educaia.dtos.EventCalendarDTO;
import com.api.educaia.models.EventCalendarModel;
import com.api.educaia.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalendarServiceImpl implements CalendarService{
    @Autowired
    private CalendarRepository calendarRepository;
    @Override
    public void createEvent(EventCalendarModel eventCalendarModel) {
        calendarRepository.save(eventCalendarModel);
    }

    @Override
    public List<EventCalendarModel> listEvents() {
        return calendarRepository.findAll();
    }

    @Override
    public void deleteEvent(UUID eventId) {
        calendarRepository.deleteById(eventId);
    }


    @Override
    public List<EventCalendarModel> getEventsAdmin(String schoolId) {
        return calendarRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public List<EventCalendarModel> getEventsStudent( String schoolId, String classId, String userId) {
        List<EventCalendarModel> eventsSchool = calendarRepository.findAllBySchoolId(schoolId);
        List<EventCalendarModel> eventsClass = calendarRepository.findAllByClassId(classId);
        List<EventCalendarModel> eventsStudent = calendarRepository.findAllByUserId(userId);
        List<EventCalendarModel> events = new ArrayList<>();

        events.addAll(eventsSchool);
        events.addAll(eventsClass);
        events.addAll(eventsStudent);
        return events;
    }

    @Override
    public List<EventCalendarModel> getEventsByClassId(String classId) {
        return calendarRepository.findAllByClassId(classId);
    }

    @Override
    public List<EventCalendarModel> getEventsTeacher(String userId, String schoolId) {
        List<EventCalendarModel> userEvents = calendarRepository.findAllByUserId(userId);
        List<EventCalendarModel> schoolEvents = calendarRepository.findAllBySchoolId(schoolId);
        userEvents.addAll(schoolEvents);
        return userEvents;
    }


}
