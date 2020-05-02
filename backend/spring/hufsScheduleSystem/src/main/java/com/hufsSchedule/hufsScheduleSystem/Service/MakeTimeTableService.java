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

    public void checkCondition(TimetableDto.Req req){
        ConditionDto.courseInstructionRes condition = conditionCheckService.checkConditionForTimeTable(req.getUserId());
        // GrdCompareService
        // TimetableDto.Req req 안에 user 데이터 들어있음

        // Credit정보 매칭 -> done
        // List<Instruction>에서 학수번호만 따로 추출 & 비교 -> yet


        // 학생의 학점 정보(Credit 객체) + 수강했던 강의 이름(List<String>) 을 불러오는 메소드
        // 자세 정보를 알고 싶다면, Dto/CondtionDto 의 courseIdRes를 체크.
        // 만든 결과물은 TimetableDto 내 res에 넣어야 함.

    }
}
