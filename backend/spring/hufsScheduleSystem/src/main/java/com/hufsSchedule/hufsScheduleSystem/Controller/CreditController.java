package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/credit")
public class CreditController {
    private final CreditService creditService;

    @GetMapping("/MyCredit")
    public Optional<Credit> getMyCredit (
            @RequestParam(value = "userId", required = true) Long userId
    ){
        return creditService.getMyCredit(userId);
    }
}
