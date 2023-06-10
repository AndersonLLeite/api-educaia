package com.api.educaia.repositories;

import com.api.educaia.models.EventCalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CalendarRepository extends JpaRepository<EventCalendarModel, UUID> {
    Optional<EventCalendarModel> findById(UUID eventId);
    List<EventCalendarModel> findAllBySchoolId(String schoolId);

    List<EventCalendarModel> findAllByClassId(String classId);

    List<EventCalendarModel> findAllByUserId(String userId);

    @Query("SELECT e FROM EventCalendarModel e " +
            "WHERE e.classId = :classId " +
            "AND e.startTime < :currentTime " +
            "AND e.endTime > :currentTime")
    List<EventCalendarModel> findByClassIdAndTime(String classId, Long currentTime);
}
