package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Dto.UserDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.ResultForm.CommonResult;
import com.hufsSchedule.hufsScheduleSystem.Service.ResponseService;
import com.hufsSchedule.hufsScheduleSystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/user")
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping("/Login")
    public CommonResult login(
            @RequestParam(value = "studentNumber", required =  true)Long studentNumber,
            @RequestParam(value = "password", required = true)String password
    )throws Exception {
        return responseService.getSingleResult(userService.login(studentNumber,password));
    }

    @PostMapping("/SignUp")
    public User signUp(@RequestBody @Valid final UserDto.SignUpReq dto) {
        return userService.signUp(dto);
    }
}
