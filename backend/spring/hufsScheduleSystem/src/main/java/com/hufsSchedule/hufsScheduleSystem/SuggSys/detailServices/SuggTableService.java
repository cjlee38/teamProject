package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRatio;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;
import org.springframework.data.relational.core.sql.In;

import java.util.*;

import static com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc.*;

public class SuggTableService {

    public static Table<String, String, WeightInstruction> initTimeTable(List<Instruction> userSelectInstructions, List<String> userSelectFreetime) {
        Table<String, String, WeightInstruction> timeTable = getEmptyTimeTable();
        List<WeightInstruction> weigted = SuggInstructionService.addWeightToInstructions(userSelectInstructions, new Float(1));

        for (String freetime : userSelectFreetime) { // 공강시간 --> empty instruction
            String day = cvtKorDayToEng(freetime.substring(0,1));
            String time = freetime.substring(1);
            timeTable.put(time, day, SuggSysFunc.getEmptyInstruction());
        }
        for (WeightInstruction instruction : weigted) { // 사용자가 설정한 강의 추가
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

    public static void inputInstructionToTable(Table<String, String, WeightInstruction> timeTable, WeightInstruction instruction)  {
        String classTime = instruction.getInstruction().getClassTime();
        String day = cvtKorDayToEng(classTime.substring(0,1));
        List<String> times = splitClassTimes(classTime.substring(2));

        for (String time : times) {
                timeTable.put(time, day, instruction);
        }

    }

    public static void inputInstructionToTable(Table<String, String, WeightInstruction> timeTable, WeightInstruction instruction, CreditRatio ratio)  {
        String field = ratio.getFieldToMajor().get(instruction.getInstruction().getDept());
        if (field != null) {
            if (ratio.getRatio().get(field) <=0) {
                return; // ratio에서 이미 채웠으면 pass
            }
        }

        for (Table.Cell cell : timeTable.cellSet()) {
            if (cell.getValue() != null
                    && ((WeightInstruction)cell.getValue()).getInstruction() != null
                    && cell.getValue().equals(instruction.getInstruction().getInstructionNumber().substring(0,6)))    {
                return; // 같은 학수번호가 존재한다면 return
            }
        }

        String classTime = instruction.getInstruction().getClassTime();
        String day = cvtKorDayToEng(classTime.substring(0,1));
        List<String> times = splitClassTimes(classTime.substring(2));
        for (String time : times) {
            if ( timeTable.get(time,day) != null) { // 해당 시간에 null이 아닌 뭔가가 있으면 pass
                return;
            }
        }

        for (String time : times) {
            timeTable.put(time, day, instruction); // 정상처리
        }
    }
    public static List<Table<String, String, WeightInstruction>> getTopNTableResult(List<Table<String, String, WeightInstruction>> tables, Integer counts) {
        List<Float> scores = new LinkedList<>();
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
        for (Table<String, String, WeightInstruction> table : tables) {
            Float score = new Float(0.0);
            for ( String row : rows) {
                for (String column : columns) {
                    WeightInstruction cell = table.get(row, column);
                    if (cell != null && ! isInstructionEmpty(cell)) {
                        score += cell.getWeight();
                    }
                }
            }
            scores.add(score);
        }

        List<Integer> indices = new ArrayList<>();
        for (Integer i = 0; i < counts; i++) {
            Float value = Collections.max(scores);
            Integer idx = scores.indexOf(value);
            indices.add(idx);
        }

        List<Table<String, String, WeightInstruction>> topNs = new ArrayList<>();
        for (Integer idx : indices) {
            topNs.add(tables.get(idx));
        }

        return topNs;
    }
}
