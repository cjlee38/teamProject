package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.table.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentNumberAndPassword(String studentNumber, String password);
    Optional<Student> findByStudentNumber(String studentNumber);
}
