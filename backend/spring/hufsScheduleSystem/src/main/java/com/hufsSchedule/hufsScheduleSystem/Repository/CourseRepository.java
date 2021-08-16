package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.Course;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
//    List<Course> findByUser(Optional<Student> user);
}
