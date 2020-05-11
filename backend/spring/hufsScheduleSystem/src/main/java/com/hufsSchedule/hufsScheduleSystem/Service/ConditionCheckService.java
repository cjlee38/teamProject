package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondService;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.CreditRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct.extractStringFromEnums;

@Service
@RequiredArgsConstructor
public class ConditionCheckService {
    private final CreditRepositorySupport creditRepositorySupport;
    private final CourseRepositorySupport courseRepositorySupport;
    private final UserRepository userRepository;

    public ConditionDto.ResultOfCondition checkCondition(Long userId){
        Optional<User> user = userRepository.findById(userId);
        Credit credit = creditRepositorySupport.findByUser(userId);
        List<Instruction> courses = courseRepositorySupport.findInstructionByUser(userId);



        ConditionDto.ResultOfCondition res = ConditionDto.ResultOfCondition.builder()
                .firstMajor(credit.getFirstMajor())
                .secondMajor(credit.getSecondMajor())
                .subMajor(credit.getSubMajor())
                .minor(credit.getMinor())
                .outDoor(credit.getOutDoor())
                .liberalArts(credit.getLiberalArts())
                .teaching(credit.getTeaching())
                .optional(credit.getOptional())
                .totalCredit(credit.getTotalCredit())
                .averageScore(credit.getAverageScore())
                .instructions(courses)
//                .grdCourses(courseList)
                .build();
        return res;
    }

    public ConditionDto.courseInstructionRes checkConditionForTimeTable(Long userId){

        Credit credit = creditRepositorySupport.findByUser(userId);
        List<Instruction> courses = courseRepositorySupport.findInstructionByUser(userId);
        ConditionDto.courseInstructionRes res = new ConditionDto.courseInstructionRes(credit, courses);
        return res;
    }

}
