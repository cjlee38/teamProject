package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRatio;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;


import java.util.*;
import java.util.stream.Collectors;




public class SuggSysFunc {
    public static boolean isInstructionEmpty(WeightInstruction instruction) {
        if (instruction.getInstruction() == null) {
            return true;
        } else {
            return false;
        }

    }

    public static WeightInstruction getEmptyInstruction() {
        return new WeightInstruction(new Float(-1));
    }


    public static String cvtKorDayToEng(String korDay) {
        String engDay;

        if (korDay.equals("월")) { engDay = "Monday"; }
        else if (korDay.equals("화")) { engDay = "Tuesday"; }
        else if (korDay.equals("수")) { engDay = "Wednesday"; }
        else if (korDay.equals("목")) { engDay = "Thursday"; }
        else if (korDay.equals("금")) { engDay = "Friday"; }
        else { engDay = null; }

        return engDay;
    }
    public static List<Instruction> sumInstructions(Map<String, List<Instruction>> instructionMap) {
        List<Instruction> courseList = new ArrayList<>();

        for (String key : instructionMap.keySet()) {
            courseList.addAll(instructionMap.get(key));
        }

        return courseList;

    }

    public static List<String> splitClassTimes(String times) {
        if (times.startsWith(" ")) {times = times.substring(1);}
        return Arrays.stream(times.split(" ")).collect(Collectors.toList());
    }

    public static Table<String, String, WeightInstruction> copyTable(Table<String, String, WeightInstruction> table) {
        List<String> columns = Lists.newArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        List<String> rows = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
        Table<String, String, WeightInstruction> copied = ArrayTable.create(rows, columns);

        copied.putAll(table);
        return copied;

    }

    public static List<WeightInstruction> copyInstructions(List<WeightInstruction> origin) {
        List<WeightInstruction> copied = new ArrayList<>();
        copied.addAll(origin);
        return copied;
    }

    public static CreditRatio copyCreditRatio(CreditRatio origin) {
        Map<String, Float> copied = new HashMap<>();
        for (String key : origin.getRatio().keySet()) {
            copied.put(key, origin.getRatio().get(key));
        }
        return new CreditRatio(origin.getFieldToMajor(), copied);
    }

    public static Integer sumTableCredit(Table<String, String, WeightInstruction> table) {
        Integer summed = 0;
        for (Table.Cell cell : table.cellSet()) {
            if (cell.getValue() != null && ((WeightInstruction)cell.getValue()).getInstruction() != null) {
                summed += ((WeightInstruction)cell.getValue()).getInstruction().getCredit();
            }
        }
        return summed;
    }

    public static List<WeightInstruction> sortInstructionByWeight(List<WeightInstruction> instructions) {
        List<WeightInstruction> sorted = new ArrayList<>();
        sorted.addAll(instructions);
        Collections.sort(instructions);

        return sorted;
    }

    public static List<String> getUserArea(User userInfo) {
        List<String> userArea = new ArrayList<>();
        userArea.add(userInfo.getMajor());

        String secondMajor = userInfo.getSecondMajor();
        String minor = userInfo.getMinor();
        Boolean teaching = userInfo.getTeaching();

        if (secondMajor != null) { userArea.add(secondMajor); }
        if (minor != null) { userArea.add(minor); }
        if (teaching == true) { userArea.add("교육학"); }

        return userArea;
    }

}
