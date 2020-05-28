package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {
    ArrayList<Instruction> findAllByRqYear(Long rqYear);
}
