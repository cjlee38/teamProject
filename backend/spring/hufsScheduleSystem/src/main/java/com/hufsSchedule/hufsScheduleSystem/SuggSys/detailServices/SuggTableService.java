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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12", "13");
        Table<String, String, WeightInstruction> timeTable = ArrayTable.create(rows, columns);

        return timeTable;
    }

    public static void inputInstructionToTable(Table<String, String, WeightInstruction> timeTable, WeightInstruction instruction)  {
        String classTime = instruction.getInstruction().getClassTime();
        List<String> times = extClassTimes(classTime);

        for (String time : times) {
            System.out.println(classTime);
            System.out.println(instruction.getInstruction().getSubject());
            timeTable.put(time.substring(1,2) , cvtKorDayToEng(time.substring(0,1)), instruction);
        }

    }

    public static Boolean inputInstructionToTable(Table<String, String, WeightInstruction> timeTable, WeightInstruction instruction, CreditRatio ratio)  {
        String field = ratio.getFieldToMajor().get(instruction.getInstruction().getDept());
        String classTime = instruction.getInstruction().getClassTime();
        if (classTime == null || classTime.length() == 0) { // classtime 없으면 return
            return false;
        }
        if (classTime.substring(0,1).equals("토")) { return false; }
        if (field != null) {
            if (ratio.getRatio().get(field) == null) {
                return false; // 없다 = 채울게 애초에 없다.
            }
            else if (ratio.getRatio().get(field) <= 0) {
                if (field.equals("secondMajor")) {
                    System.out.println("when input --....");
                    System.out.println(field + instruction.getInstruction().getSubject());
                    System.out.println(ratio.getRatio());
                }


                return false; // ratio에서 이미 채웠으면 pass
            }

        }
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Saturday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12", "13");
        for (String row : rows) {
            for (String column : columns) {
                WeightInstruction cell = timeTable.get(row, column);
                if (cell != null && !isInstructionEmpty(cell)) {
                    String instNumber = cell.getInstruction().getInstructionNumber().substring(0, 6);
                    if (instNumber.equals(instruction.getInstruction().getInstructionNumber().substring(0, 6))) {
                        return false; // 같은 학수번호가 존재한다면 return
                    }
                }
            }
        }



//        System.out.println("classtime -----------------------");
//        System.out.println(classTime);
//        System.out.println(instruction.getInstruction().getInstructionNumber());
//        System.out.println(instruction.getInstruction().getSubject());
//        System.out.println("--------------------------------");

        List<String> times = extClassTimes(classTime);

        for (String time : times) {
            if (timeTable.get(time.substring(1,2), cvtKorDayToEng(time.substring(0,1))) != null) {// 해당 시간에 null이 아닌 뭔가가 있으면 pass
                return false;
            }
        }

        try{
            for (String time : times) {
                timeTable.put(time.substring(1,2), cvtKorDayToEng(time.substring(0,1)), instruction);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(times);
            System.out.println(instruction.getInstruction().getSubject());
            System.out.println(instruction.getInstruction().getInstructionNumber());
            throw new NullPointerException("shit");
        }


        return true;
    }
    public static List<Table<String, String, WeightInstruction>> getTopNTableResult(List<Table<String, String, WeightInstruction>> tables, Integer counts) {
        //remove duplication
        List<Table<String, String, WeightInstruction>> unq = new ArrayList<>();
        for (Table<String, String, WeightInstruction> table : tables) {
            if (!unq.contains(table)) {
                unq.add(table);
            }
        }
        System.out.println("duplicated size : " + tables.size());
        System.out.println("unique size : " + unq.size());

        if (unq.size() < counts) {
            return unq;
        }
        unq = tables;


        List<Integer> required = new ArrayList<>();
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12", "13");
        for (Table<String, String, WeightInstruction> table : unq) {
            Integer require = new Integer(0);
            for ( String row : rows) {
                for (String column : columns) {
                    WeightInstruction cell = table.get(row, column);
                    if (cell != null && ! isInstructionEmpty(cell)) {
                        boolean rq = cell.getInstruction().isRequired();
                        if (rq == true) {
                            require += 1;
                        }
                    }
                }
            }
            required.add(require);
        }
        System.out.println(required);
//        List<Integer> indices = new ArrayList<>();
        List<Table<String, String, WeightInstruction>> sorting = new ArrayList<>();
        for (int i = 0; i < required.size(); i++) {
            int val = Collections.max(required);
            int idx = required.indexOf(val);
            sorting.add(unq.get(idx));
            required.remove(idx);
            unq.remove(idx);
        }



        List<Float> scores = new ArrayList<>();
        for (Table<String, String, WeightInstruction> table : sorting) {
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

        List<Table<String, String, WeightInstruction>> topNs = new ArrayList<>();
        for (Integer i = 0; i < counts; i++) {
            if (scores.size() == 0) {
                break;
            }
            Float value = Collections.max(scores);
            int idx = scores.indexOf(value);
            scores.remove(idx);
            topNs.add(sorting.get(idx));
            sorting.remove(idx);
            System.out.println("suggTaleServbice, getTopN, score/idx" + value.toString() +" ~ "+ idx);
        }

        return topNs;
    }
}
