package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;

import java.util.List;

public class SuggTableService {

    public Table<String, String, Instruction> initTimeTable(List<Instruction> userSelectCourses, Object userSelectFreetime) {
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
        Table<String, String, Instruction> timeTable = ArrayTable.create(rows, columns);

        // do something with user select free time



        SuggSysFunc.inputCourseToTable(timeTable, userSelectCourses);

        return timeTable;
    }
}
