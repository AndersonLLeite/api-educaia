package com.api.educaia.services;

import com.api.educaia.dtos.ClassHappeningRightNowDTO;
import com.api.educaia.dtos.EventCalendarDTO;
import com.api.educaia.models.EventCalendarModel;
import com.api.educaia.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        Set<EventCalendarModel> events = new HashSet<>();

        List<EventCalendarModel> eventsSchool = calendarRepository.findAllBySchoolId(schoolId);
        List<EventCalendarModel> eventsClass = calendarRepository.findAllByClassId(classId);
        List<EventCalendarModel> eventsStudent = calendarRepository.findAllByUserId(userId);

        events.addAll(eventsSchool);
        events.addAll(eventsClass);
        events.addAll(eventsStudent);

        return new ArrayList<>(events);
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

    @Override
    public Optional<EventCalendarModel> getEventByEventId(UUID eventId) {
        return calendarRepository.findById(eventId);
    }

    @Override
    public void updateEventRecurrentAddExceptionDate(EventCalendarModel eventCalendarModel, Long exceptionDate) {
        eventCalendarModel.addExceptionDate(exceptionDate);
        calendarRepository.save(eventCalendarModel);
    }

    @Override
    public ClassHappeningRightNowDTO getClassHappeningRightNow(String classId) {

        Date now = new Date();
        Long nowLong = now.getTime();
        List<EventCalendarModel> events = calendarRepository.findByClassIdAndTime(classId, nowLong);
        if(events.size() > 0){
            EventCalendarModel event = events.get(0);
            return new ClassHappeningRightNowDTO(event.getSubject(), event.getStartTime(), event.getEndTime());
        }
        return null;
    }


}
