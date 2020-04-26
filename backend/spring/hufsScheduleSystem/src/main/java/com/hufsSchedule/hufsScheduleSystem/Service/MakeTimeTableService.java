package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MakeTimeTableService {
    private final ConditionCheckService conditionCheckService;

    public void checkCondition(Long userId){
        ConditionDto.courseIdRes condition = conditionCheckService.checkConditionForTimeTable(userId);
        // GrdCond.Major major = new Business()

        // 전공클래스 / 교양클래스로부터 데이터 받아오고
        // e.g. Major Buisness = new Business(init by condition);
        // LiberalArts liberal = new liberal2015(init by condition);
        // 비교작업하고 --> 여기서?
        // 나머지 강의 / 학점 정보 전달.

        // 학생의 학점 정보(Credit 객체) + 수강했던 강의 이름(List<String>) 을 불러오는 메소드
        // 자세 정보를 알고 싶다면, Dto/CondtionDto 의 courseIdRes를 체크.
        // 만든 결과물은 TimetableDto 내 res에 넣어야 함.

    }
}
