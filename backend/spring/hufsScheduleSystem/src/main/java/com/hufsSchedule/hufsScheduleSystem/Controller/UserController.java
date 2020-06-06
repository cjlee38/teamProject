package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.ResultForm.CommonResult;
import com.hufsSchedule.hufsScheduleSystem.Service.ResponseService;
import com.hufsSchedule.hufsScheduleSystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping("/Login")
    public CommonResult login(
            @RequestParam(value = "studentNumber", required =  true)String studentNumber,
            @RequestParam(value = "password", required = true)String password
    )throws Exception {
        return responseService.getSingleResult(userService.login(studentNumber,password));
    }

    @PostMapping("/SignUp")
    public boolean signUp(@RequestBody UserDto.SignUpReq dto ) {
        return userService.signUp(dto);
    }
}
