package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByStudentNumberAndPassword(String studentNumber, String password);
    Optional<User> findByStudentNumber(String studentNumber);
}
