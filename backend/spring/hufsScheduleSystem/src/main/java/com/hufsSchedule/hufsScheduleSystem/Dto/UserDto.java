package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq{
        private String studentNumber;
        @NotBlank(message = "비밀번호를 작성해주세요.")
        private String password;

        @Builder
        public SignUpReq(String studentNumber, String password){
            this.studentNumber = studentNumber;
            this.password = password;
        }

        public User toEntity() {
            return User.builder()
                    .studentNumber(this.studentNumber)
                    .password(this.password)
                    .build();
        }
    }

    @Getter
    public static class Res {

    }

}
