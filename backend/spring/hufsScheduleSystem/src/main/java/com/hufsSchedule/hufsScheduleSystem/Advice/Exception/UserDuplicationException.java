package com.hufsSchedule.hufsScheduleSystem.Advice.Exception;

import lombok.Getter;

@Getter
public class UserDuplicationException extends RuntimeException {
    private Long studentNumber;
    private String msg;

    public UserDuplicationException(Long studentNumber){
        this.studentNumber = studentNumber;
        this.msg = "중복되는 학번입니다.";
    }
}
