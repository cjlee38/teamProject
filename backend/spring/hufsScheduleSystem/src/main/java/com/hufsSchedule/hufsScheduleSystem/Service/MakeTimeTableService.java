package com.hufsSchedule.hufsScheduleSystem.Service;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCompareService;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondService;
import com.hufsSchedule.hufsScheduleSystem.Redis.RedisService;
import com.hufsSchedule.hufsScheduleSystem.Repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MakeTimeTableService {
    private final ConditionCheckService conditionCheckService;
    private final InstructionRepository instructionRepository;
    private final RedisService redisService;
    private GrdCondService grdCondService;
    private GrdCompareService grdCompareService;
    private TimetableDto.Res res;

    public void checkCondition(TimetableDto.Req req){
        ConditionDto.courseInstructionRes condition = conditionCheckService.checkConditionForTimeTable(req.getUserId());
        ArrayList<Instruction> instructions = instructionRepository.findAllByRqYear(20L); //20년도 강의목록입니다.

        //GrdCondObj GrdCond = grdCondService.makeGrdCondByUserInfo(req);
        //GrdCondObj remains = grdCompareService.compareGrdAndUser(req, condition, GrdCond);
        //res = new TimetableDto.Res(remains);

        // TimetableDto.Req req 안에 user 데이터 들어있음


        // 학생의 학점 정보(Credit 객체) + 수강했던 강의 이름(List<String>) 을 불러오는 메소드
        // 자세 정보를 알고 싶다면, Dto/CondtionDto 의 courseIdRes를 체크.
        // 만든 결과물은 TimetableDto 내 res에 넣어야 함.

    }
}
