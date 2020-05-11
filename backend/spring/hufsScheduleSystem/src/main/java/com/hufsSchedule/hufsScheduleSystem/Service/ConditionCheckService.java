package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserNotFoundException;
import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.CreditRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ConditionCheckService {
    private final CreditRepositorySupport creditRepositorySupport;
    private final CourseRepositorySupport courseRepositorySupport;
    private final UserRepository userRepository;

    public ConditionDto.ResultOfCondition checkCondition(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Credit credit = creditRepositorySupport.findByUser(userId);
        List<Instruction> courses = courseRepositorySupport.findInstructionByUser(userId);
        List<String> instructionName = courses.stream().map(Instruction::getSubject).collect(Collectors.toList());

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
                .instructions(instructionName)
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
