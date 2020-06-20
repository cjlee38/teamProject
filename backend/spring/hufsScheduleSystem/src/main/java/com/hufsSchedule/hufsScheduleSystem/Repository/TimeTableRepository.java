package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.Course;
import com.hufsSchedule.hufsScheduleSystem.Entity.Timetable;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> deleteAllByUser(User user);
}
