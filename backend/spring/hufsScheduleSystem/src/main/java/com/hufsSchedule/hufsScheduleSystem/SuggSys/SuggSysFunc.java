package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.QInstruction;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SuggSysFunc {
    public static Table<String, String, Instruction> inputCourseToTable(Table timeTable, List<Instruction> inputCourses)  {
        for (Instruction course : inputCourses) {
            String classTime = course.getClassTime();
            String day = convertKorToEng(classTime.substring(0,1));
            List<String> times = splitClassTimes(classTime.substring(1));

            for (String time : times) {
                timeTable.put(time, day, course);
            }
        }

        return timeTable;
    }

    public static String convertKorToEng(String korDay) {
        String engDay;

        if (korDay.equals("월")) { engDay = "Monday"; }
        else if (korDay.equals("화")) { engDay = "TuesDay"; }
        else if (korDay.equals("수")) { engDay = "Wednesday"; }
        else if (korDay.equals("목")) { engDay = "Thursday"; }
        else if (korDay.equals("금")) { engDay = "Friday"; }
        else { engDay = null; }

        return engDay;

    }

    public static List<String> splitClassTimes(String times) {
        return Arrays.stream(times.split("")).collect(Collectors.toList());
    }


}
