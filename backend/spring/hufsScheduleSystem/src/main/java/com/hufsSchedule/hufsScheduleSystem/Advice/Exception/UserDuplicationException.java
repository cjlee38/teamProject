package com.hufsSchedule.hufsScheduleSystem.Advice.Exception;

import lombok.Getter;

@Getter
public class UserDuplicationException extends RuntimeException {
    private String studentNumber;
    private String msg;

    public UserDuplicationException(String studentNumber){
        this.studentNumber = studentNumber;
        this.msg = "중복되는 학번입니다.";
    }
}
