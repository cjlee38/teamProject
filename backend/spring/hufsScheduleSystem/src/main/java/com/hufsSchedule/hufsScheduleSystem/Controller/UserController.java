package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
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

    @PostMapping("/Login")
    public CommonResult login(@RequestBody UserDto.loginReq login) throws Exception {
        return responseService.getSingleResult(userService.login(login));
    }

    @PostMapping("/SignUp")
    public boolean signUp(@RequestBody UserDto.SignUpReq dto ) {
        return userService.signUp(dto);
    }

}
