package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<Timetable, Long> {
//    @Transactional
//    @Modifying
//    void deleteAllByUser(Student student);
}
