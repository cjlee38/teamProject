package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class loginReq{
        private String studentNumber;
        private String password;

        @Builder
        public loginReq(String studentNumber, String password){
            this.studentNumber = studentNumber;
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq{
        private String studentNumber;
        private String password;
        private String name;

        @Builder
        public SignUpReq(String studentNumber, String password, String name){
            this.studentNumber = studentNumber;
            this.password = password;
            this.name = name;
        }

        public User toEntity() {
            return User.builder()
                    .studentNumber(this.studentNumber)
                    .password(this.password)
                    .name(this.name)
                    .build();
        }
    }

    @Getter
    public static class Res {
        private Long userId;
        private String password;
        private String studentNumber;
        private String name;
        private String major;
        private String secondMajor;
        private String minor;
        private Integer year;
        private Boolean foreigner;
        private Boolean teaching;

        @Builder
        public Res(Long userId, String password, String studentNumber, String name, String major, String secondMajor, String minor, int year, boolean foreigner, boolean teaching){
            this.userId = userId;
            this.password = password;
            this.studentNumber = studentNumber;
            this.name = name;
            this.major = major;
            this.secondMajor = secondMajor;
            this.minor = minor;
            this.year = year;
            this.foreigner = foreigner;
            this.teaching = teaching;
        }
    }

}
