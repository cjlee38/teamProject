package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Redis.RedisService;
import com.hufsSchedule.hufsScheduleSystem.Service.MakeTimeTableService;
import com.hufsSchedule.hufsScheduleSystem.Service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/makeTimeTable")
@CrossOrigin(origins = "*")
public class MakeTimeTableController {
    private final ResponseService responseService;
    private final MakeTimeTableService makeTimeTableService;
    private final RedisService redisService;

    /*@GetMapping("/selectInstruction")
    public void makeTimeTable()throws Exception {
        redisService.InstallInstructions();
        return ;
    }*/

    @PostMapping("/try")
    public void makeTimeTable(@RequestBody TimetableDto.Req req)throws Exception {
        makeTimeTableService.checkCondition(req);
        return ;
    }

    @PostMapping("/save")
    public void saveTimeTable(@RequestBody TimetableDto.Req req)throws Exception {
        return ;
    }
}
