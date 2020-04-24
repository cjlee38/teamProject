package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Entity.Course;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepository;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final UserRepository userRepository;

    public User userFindByUserId(Long userId){
        final Optional<User> user = userRepository.findById(userId);
        return user.get();
    }

    public List<String> getMyCourses(Long userId){
        User user = userFindByUserId(userId);
        List<Course> courses = user.getCourses();
        List<String> instructions = new ArrayList<String>();
        courses.stream().forEach(x -> instructions.add(x.getInstruction().getSubject()));
        return instructions;
    }
    //학생이 수강한 강의 제목을 리스트 형태로 반환
    public List<Long> getMyCoursesId(Long userId){
        User user = userFindByUserId(userId);
        List<Course> courses = user.getCourses();
        List<Long> instructionsId = new ArrayList<Long>();
        courses.stream().forEach(x-> instructionsId.add(x.getInstruction().getInstructionId()));
        return instructionsId;
    }
    //학생이 수강한 강의ID를 리스트 형태로 반환
}
