package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConditionCheckService {
    private final CreditService creditService;
    private final CourseService courseService;

    public ConditionDto.courseNameRes checkCondition(Long userId){
        final Credit credit = creditService.getMyCredit(userId);
        final List<String> instructions = courseService.getMyCourses(userId);
        ConditionDto.courseNameRes res = new ConditionDto.courseNameRes(credit, instructions);
        return res;
    }

    public ConditionDto.courseIdRes checkConditionForTimeTable(Long userId){
        final Credit credit = creditService.getMyCredit(userId);
        final List<Long> instructionsId = courseService.getMyCoursesId(userId);
        ConditionDto.courseIdRes res = new ConditionDto.courseIdRes(credit, instructionsId);
        return res;
    }

}
