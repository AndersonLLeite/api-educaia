package com.api.educaia.controllers;

import com.api.educaia.dtos.EventCalendarDTO;
import com.api.educaia.models.EventCalendarModel;
import com.api.educaia.services.CalendarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping("/create-event")
        public ResponseEntity<?> createEvent(@RequestBody EventCalendarDTO eventCalendarDTO) {
            var eventCalendarModel = new EventCalendarModel();
            BeanUtils.copyProperties(eventCalendarDTO, eventCalendarModel);
            calendarService.createEvent(eventCalendarModel);

            return new ResponseEntity<EventCalendarModel>(eventCalendarModel, HttpStatus.CREATED);
        }

    @DeleteMapping("/delete-event/{eventId}")
public ResponseEntity<?> deleteEvent(@PathVariable("eventId") UUID eventId) {
        calendarService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO lista para testes
    @GetMapping("/get-events")
    public ResponseEntity<?> listEvents() {
        return new ResponseEntity<>(calendarService.listEvents(), HttpStatus.OK);
    }

    @GetMapping("/get-events-admin/{schoolId}")
    public ResponseEntity<?> getEventsAdmin( @PathVariable("schoolId") String schoolId) {
        return new ResponseEntity<>(calendarService.getEventsAdmin(schoolId), HttpStatus.OK);
    }

    @GetMapping("/get-events-student/{schoolId}/{classId}/{userId}")
    public ResponseEntity<?> getEventsStudent( @PathVariable("schoolId") String schoolId, @PathVariable("classId") String classId, @PathVariable("userId") String userId) {
        return new ResponseEntity<>(calendarService.getEventsStudent(schoolId, classId, userId ), HttpStatus.OK);
    }

    @GetMapping("/get-events-teacher/{userId}/{schoolId}")
    public ResponseEntity<?> getEventsTeacher(@PathVariable("userId") String userId, @PathVariable("schoolId") String schoolId) {
        return new ResponseEntity<>(calendarService.getEventsTeacher(userId, schoolId), HttpStatus.OK);
    }

    @GetMapping("/get-events-class/{classId}")
    public ResponseEntity<?> getEventsByClassId(@PathVariable("classId") String classId) {
        return new ResponseEntity<>(calendarService.getEventsByClassId(classId), HttpStatus.OK);
    }


}
