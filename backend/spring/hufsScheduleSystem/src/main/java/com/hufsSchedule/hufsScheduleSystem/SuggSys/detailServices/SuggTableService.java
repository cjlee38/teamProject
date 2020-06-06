package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRatio;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.List;

import static com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc.cvtKorDayToEng;
import static com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc.splitClassTimes;

public class SuggTableService {

    public Table<String, String, WeightInstruction> initTimeTable(List<Instruction> userSelectInstructions, Object userSelectFreetime) {
        Table<String, String, WeightInstruction> timeTable = getEmptyTimeTable();
        List<WeightInstruction> weigted = SuggInstructionService.addWeightToInstructions(userSelectInstructions, new Float(1));
        // do something with user select free time

        for (WeightInstruction instruction : weigted) {
            inputInstructionToTable(timeTable, instruction);
        }

        return timeTable;
    }

    public static Table<String, String, WeightInstruction> getEmptyTimeTable() {
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
        Table<String, String, WeightInstruction> timeTable = ArrayTable.create(rows, columns);

        return timeTable;
    }

    public void inputInstructionToTable(Table<String, String, WeightInstruction> timeTable, WeightInstruction instruction)  {
        String classTime = instruction.getInstruction().getClassTime();
        String day = cvtKorDayToEng(classTime.substring(0,1));
        List<String> times = splitClassTimes(classTime.substring(1));

        for (String time : times) {
                timeTable.put(time, day, instruction);
        }

    }

    public void inputInstructionToTable(Table<String, String, WeightInstruction> timeTable, WeightInstruction instruction, CreditRatio ratio)  {
        String field = ratio.getFieldToMajor().get(instruction.getInstruction().getDept());
        if (field != null) {
            if (ratio.getRatio().get(field) <=0) {
                return; // ratio에서 이미 채웠으면 pass
            }
        }

        for (Table.Cell cell : timeTable.cellSet()) {
            if (cell.getValue() != null && cell.getValue().equals(instruction.getInstruction().getInstructionNumber().substring(0,6)))    {
                return; // 같은 학수번호가 존재한다면 return
            }
        }

        String classTime = instruction.getInstruction().getClassTime();
        String day = cvtKorDayToEng(classTime.substring(0,1));
        List<String> times = splitClassTimes(classTime.substring(1));
        for (String time : times) {
            if ( timeTable.get(time,day) != null) { // 해당 시간에 null이 아닌 뭔가가 있으면 pass
                return;
            }
        }

        for (String time : times) {
            timeTable.put(time, day, instruction); // 정상처리
        }


    }
}
