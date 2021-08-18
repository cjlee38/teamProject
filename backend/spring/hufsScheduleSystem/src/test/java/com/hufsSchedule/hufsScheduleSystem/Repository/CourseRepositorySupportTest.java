package com.hufsSchedule.hufsScheduleSystem.Repository;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.Course;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.domain.entity.User;
import com.hufsSchedule.hufsScheduleSystem.domain.type.DepartmentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositorySupportTest {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseRepositorySupport courseRepositorySupport;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstructionRepository instructionRepository;

    @Test
    public void getInstructionsWithUserId() throws Exception {
        // given
        User user = new User();
        user.setUsername("hello");
        user.setPassword("password");
        userRepository.save(user);

        Instruction instruction = new Instruction();
        instruction.setDepartment(DepartmentType.BUSINESS);
        instructionRepository.save(instruction);

        Course course = new Course();
        course.setInstruction(instruction);
        course.setUser(user);
        courseRepository.save(course);

        // when

        List<Instruction> instructions = courseRepositorySupport.findInstructionByUser(user.getId());

        // then
        assertEquals(1, instructions.size());
        assertEquals(instructions.get(0).getDepartment(), DepartmentType.BUSINESS);
    }
}