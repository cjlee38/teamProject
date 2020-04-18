package com.hufsSchedule.hufsScheduleSystem.Controller;

import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/Login")
    public Optional<User> login(
            @RequestParam(value = "studentNumber", required =  true)Long studentNumber,
            @RequestParam(value = "password", required = true)String password) {
        return userService.login(studentNumber,password);
    }
}
