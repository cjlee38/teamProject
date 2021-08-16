package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<Student> findByNumberAndPassword(String studentNumber, String password);
//    Optional<Student> findByNumber(String studentNumber);
}
