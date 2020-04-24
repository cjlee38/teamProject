package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.ResultForm.CommonResult;
import com.hufsSchedule.hufsScheduleSystem.Service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/makeTimeTable")
public class MakeTimeTableController {
    private final ResponseService responseService;

    @PostMapping("/try")
    public void makeTimeTable(@RequestBody TimetableDto.Req req)throws Exception {
        return ;
    }
}
