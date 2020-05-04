package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class TimetableDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{
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
        public Req(Long userId, String password, String studentNumber, String name, String major, String secondMajor, String minor, int year, boolean foreigner, boolean teaching){
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

    @Getter
    public static class Res {

    }

}