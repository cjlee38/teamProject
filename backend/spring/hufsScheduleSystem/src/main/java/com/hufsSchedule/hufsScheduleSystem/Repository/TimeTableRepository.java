package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.table.Student;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface TimeTableRepository extends JpaRepository<Timetable, Long> {
    @Transactional
    @Modifying
    void deleteAllByUser(Student student);
}
