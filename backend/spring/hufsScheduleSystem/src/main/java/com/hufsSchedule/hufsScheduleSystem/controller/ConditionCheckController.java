package com.hufsSchedule.hufsScheduleSystem.controller;

import com.hufsSchedule.hufsScheduleSystem.ResultForm.CommonResult;
import com.hufsSchedule.hufsScheduleSystem.Service.ConditionCheckService;
import com.hufsSchedule.hufsScheduleSystem.Service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/checkCondition")
@CrossOrigin(origins = "*")
public class ConditionCheckController {
    private final ResponseService responseService;
    private final ConditionCheckService conditionCheckService;

    @GetMapping("/try")
    public CommonResult checkMyCondition(@RequestParam(value = "userId") Long userId) throws Exception {
        return responseService.getSingleResult(conditionCheckService.checkCondition(userId));
    }
}
