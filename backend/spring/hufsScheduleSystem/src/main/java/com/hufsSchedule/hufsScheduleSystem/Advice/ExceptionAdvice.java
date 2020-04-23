package com.hufsSchedule.hufsScheduleSystem.Advice;

import com.hufsSchedule.hufsScheduleSystem.Advice.Exception.UserNotFoundException;
import com.hufsSchedule.hufsScheduleSystem.ResultForm.CommonResult;
import com.hufsSchedule.hufsScheduleSystem.Service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult defaultException(HttpServletRequest request, UserNotFoundException e){

        return responseService.getFailResult();
    }


}
