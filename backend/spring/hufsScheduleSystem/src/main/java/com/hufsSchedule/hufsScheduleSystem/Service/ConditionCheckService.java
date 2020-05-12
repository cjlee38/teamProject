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

import java.util.ArrayList;
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
        List<String> userInfo = new ArrayList<>();
        List<Integer> userGrdCredit = new ArrayList<>();
        List<Integer> userRemainCredit = new ArrayList<>();
        List<Integer> userTakenCredit = new ArrayList<>();

        userInfo.add(user.getMajor());
        userInfo.add(user.getSecondMajor());
        userInfo.add(user.getMinor());

        userTakenCredit.add(credit.getFirstMajor());
        userTakenCredit.add(credit.getSecondMajor());
        userTakenCredit.add(credit.getSubMajor());
        userTakenCredit.add(credit.getMinor());
        userTakenCredit.add(credit.getOutDoor());
        userTakenCredit.add(credit.getLiberalArts());
        userTakenCredit.add(credit.getTeaching());
        userTakenCredit.add(credit.getOptional());

        GrdCondObj GrdObj = GrdCondService.makeGrdCondByUserInfo(user);
        List<String> gCourses = new ArrayList<>();
        gCourses.addAll(GrdCondEct.extractStringFromEnums(GrdObj.getGrdCourse().get("firstMajor")));
        gCourses.addAll(GrdCondEct.extractStringFromEnums(GrdObj.getGrdCourse().get("secondMajor")));
        gCourses.addAll(GrdCondEct.extractStringFromEnums(GrdObj.getGrdCourse().get("liberalArts")));
//        List<String> gCourses = GrdCondEct.extractStringFromEnums(GrdObj.getGrdCourse());
        CreditCondObj gCredit = GrdObj.getGrdCredit();

        userGrdCredit.add(gCredit.getFirstMajor());
        userGrdCredit.add(gCredit.getSecondMajor());
        userGrdCredit.add(gCredit.getSubMajor());
        userGrdCredit.add(gCredit.getMinor());
        userGrdCredit.add(gCredit.getOutDoor());
        userGrdCredit.add(gCredit.getLiberalArts());
        userGrdCredit.add(gCredit.getTeaching());
        userGrdCredit.add(gCredit.getOptional());
        userGrdCredit.add(gCredit.getTotalCredit());

        GrdCondObj remainObj = GrdCompareService.compareGrdAndUser(user, courses, credit, GrdObj);
        List<String> remainCourses = new ArrayList<>();

        List<String> temp = GrdCondEct.extractStringFromEnums(remainObj.getGrdCourse().get("firstMajor"));
        for(String s : temp) {
            System.out.println(s);
        }
        List<String> temp2 = GrdCondEct.extractStringFromEnums(remainObj.getGrdCourse().get("secondMajor"));
        for(String s : temp2) {
            System.out.println(s);
        }
        List<String> temp3 = GrdCondEct.extractStringFromEnums(remainObj.getGrdCourse().get("liberalArts"));
        for(String s : temp3) {
            System.out.println(s);
        }

        remainCourses.addAll(GrdCondEct.extractStringFromEnums(remainObj.getGrdCourse().get("firstMajor")));
        remainCourses.addAll(GrdCondEct.extractStringFromEnums(remainObj.getGrdCourse().get("secondMajor")));
        remainCourses.addAll(GrdCondEct.extractStringFromEnums(remainObj.getGrdCourse().get("liberalArts")));
//
        CreditCondObj remainCredit = remainObj.getGrdCredit();

        userRemainCredit.add(remainCredit.getFirstMajor());
        userRemainCredit.add(remainCredit.getSecondMajor());
        userRemainCredit.add(remainCredit.getSubMajor());
        userRemainCredit.add(remainCredit.getMinor());
        userRemainCredit.add(remainCredit.getOutDoor());
        userRemainCredit.add(remainCredit.getLiberalArts());
        userRemainCredit.add(remainCredit.getTeaching());
        userRemainCredit.add(remainCredit.getOptional());
        userRemainCredit.add(remainCredit.getTotalCredit());

        ConditionDto.ResultOfCondition res = ConditionDto.ResultOfCondition.builder()
                .userInfo(userInfo)
                .takenCredit(userTakenCredit)
                .remainCredit(userRemainCredit)
                .grdCredit(userGrdCredit)
                .instructions(instructionName)
                .averageScore(credit.getAverageScore())
                .grdCourses(gCourses)
                .grdAverageScore(gCredit.getAverageScore())
                .remainCourses(remainCourses)
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
