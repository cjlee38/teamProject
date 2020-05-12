package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserNotFoundException;
import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCompareService;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondService;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.CreditRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



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

        GrdCondObj GrdObj = GrdCondService.makeGrdCondByUserInfo(user);
        List<String> gCourses = GrdCondEct.extractStringFromEnums(GrdObj.getGrdCourse());
        CreditCondObj gCredit = GrdObj.getGrdCredit();

        GrdCondObj remainObj = GrdCompareService.compareGrdAndUser(user, courses, credit, GrdObj);
        List<String> remainCourses = GrdCondEct.extractStringFromEnums(remainObj.getGrdCourse());
        CreditCondObj remainCredit = remainObj.getGrdCredit();


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

                .grdCourses(gCourses)
                .grdFirstMajor(gCredit.getFirstMajor())
                .grdSecondMajor(gCredit.getSecondMajor())
                .grdSubMajor(gCredit.getSubMajor())
                .grdMinor(gCredit.getMinor())
                .grdOutDoor(gCredit.getOutDoor())
                .grdLiberalArts(gCredit.getLiberalArts())
                .grdTeaching(gCredit.getTeaching())
                .grdOptional(gCredit.getOptional())
                .grdTotalCredit(gCredit.getTotalCredit())
                .grdAverageScore(gCredit.getAverageScore())

                .remainCourses(remainCourses)
                .remainFirstMajor(remainCredit.getFirstMajor())
                .remainSecondMajor(remainCredit.getSecondMajor())
                .remainSubMajor(remainCredit.getSubMajor())
                .remainMinor(remainCredit.getMinor())
                .remainOutdoor(remainCredit.getOutDoor())
                .remainLiberalArts(remainCredit.getLiberalArts())
                .remainTeaching(remainCredit.getTeaching())
                .remainOptional(remainCredit.getOptional())
                .remainTotalCredit(remainCredit.getTotalCredit())
                .remainAverageScore(remainCredit.getAverageScore())
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
