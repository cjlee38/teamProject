package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Redis.RedisService;
import com.hufsSchedule.hufsScheduleSystem.Repository.CourseRepositorySupport;
import com.hufsSchedule.hufsScheduleSystem.Service.MakeTimeTableService;
import com.hufsSchedule.hufsScheduleSystem.Service.ResponseService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/makeTimeTable")
@CrossOrigin(origins = "*")
public class MakeTimeTableController {
    private final ResponseService responseService;
    private final MakeTimeTableService makeTimeTableService;
    private final RedisService redisService;
    private final CourseRepositorySupport courseRepositorySupport;

    @PostMapping("/try")
    public TimetableDto.Result makeTimeTable(@RequestBody TimetableDto.Req req) throws Exception {
        TimetableDto.Result results = makeTimeTableService.checkCondition(req);
        return results;
    }

    @PostMapping("/save")
    public boolean saveTimeTable(@RequestBody TimetableDto.SaveTimeTable req) throws Exception {
        makeTimeTableService.saveTimeTable(req);
        return true;
    }

    @GetMapping("/check")
    public TimetableDto.MyTimeTable checkTimeTable(@RequestParam(value = "userId", required =  true)Long userId)throws Exception {
        TimetableDto.MyTimeTable result = makeTimeTableService.checkTimeTable(userId);
        return result;
    }

    @GetMapping("/test")
    public List<TimetableDto.findInstructionCode> test(String area)throws Exception {
        return courseRepositorySupport.findInstructionCodeByMajor(area);
    }
}
