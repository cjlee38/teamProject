package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.Course;
import com.hufsSchedule.hufsScheduleSystem.Entity.Timetable;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface TimeTableRepository extends JpaRepository<Timetable, Long> {
    @Transactional
    @Modifying
    void deleteAllByUser(User user);
}
