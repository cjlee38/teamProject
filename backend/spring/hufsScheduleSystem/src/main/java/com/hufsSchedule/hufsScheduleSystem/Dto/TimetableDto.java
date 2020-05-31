package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondObj;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

public class TimetableDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{
        private Long userId;
        private ArrayList<String> myFreetime;// 공강시간

       @Builder
        public Req(Long userId, ArrayList<String> mytime){
            this.userId = userId;
            this.myFreetime = mytime;
        }
    }

    @Getter
    public static class Res {
        private GrdCondObj remains;

        public Res(GrdCondObj remains) {
            this.remains = remains;
        }

    }

    @Getter
    public static class Result {
        private ArrayList<Day> timeTable;

        public Result(ArrayList<Day> timeTable) {
            this.timeTable = timeTable;
        }
    }

    public static class Data {
        private String instruction;
        private String instructor;
        private Integer required;

        public Data(String instruction, String instructor, Integer required){
            this.instruction = instruction;
            this.instructor = instructor;
            this.required = required;
        }
    }

    public static class Day {
        private ArrayList<Data> data;
        private Boolean is;

        public Day(Boolean is){
            this.is = is;
        }

        public Day(ArrayList<Data> data){
            this.data = data;
        }
    }
    /*
    예.
        [
            [[“자료구조“, “최정주“, 1], [“자료구조“, “최정주“, 1], [“자료구조“, “최정주“, 1], false, false, false, false, false, false, false, false, false],	# 월
            [false, false, false, false, false, false,false, false, false, false, false, false],	# 화
            [false, false, false, false, false, false,false, false, false, false, false, false],	# 수
            [false, false, false, false, false, false,false, false, false, false, false, false],	# 목
            [false, false, false, false, false, false,false, false, false, false, false, false],	# 금
        ]
    결과 테이블이 월 123 최정주의 자료구조이고 필수조건이 true이면 위와 같이 보냅니다.
    테이블 형성 과정은 이렇습니다.
    1. TimetableDto.Data data1 = new TimetableDto.Data("자료구조", "최정주", 1);
    2. TimetableDto.Day monday = new TimeTableDto.Day();
    3. for(int i = 0; i < 12; i++) {
           monday.add(false);
       }
    4. monday.set(0, data1);
       monday.set(1, data1);
       monday.set(2, data1);

      요런식으로 화수목금 채워서.... ㅎ
      프론트에서 요구하는게 위와 같은 데이터 형식이라.....
    * */
}
