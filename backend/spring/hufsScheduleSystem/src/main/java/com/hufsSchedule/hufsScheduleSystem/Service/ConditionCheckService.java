package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.CreditRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConditionCheckService {
    private final CreditRepositorySupport creditRepositorySupport;
    private final CourseRepositorySupport courseRepositorySupport;

    public ConditionDto.ResultOfCondition checkCondition(Long userId){
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
