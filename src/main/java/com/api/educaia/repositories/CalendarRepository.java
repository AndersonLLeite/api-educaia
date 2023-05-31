package com.api.educaia.repositories;

import com.api.educaia.models.EventCalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CalendarRepository extends JpaRepository<EventCalendarModel, UUID> {
    List<EventCalendarModel> findAllBySchoolId(String schoolId);

    List<EventCalendarModel> findAllByClassId(String classId);

    List<EventCalendarModel> findAllByUserId(String userId);
}
