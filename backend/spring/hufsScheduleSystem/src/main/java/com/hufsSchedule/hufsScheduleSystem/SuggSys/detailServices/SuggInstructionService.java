package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SuggInstructionService {
    // 수강한과목 & 선택한과목 삭제.
    /*
    본인 전공에 해당하지 않는 과목 삭제할것인지..?
     */
    public List<Instruction> initValidInstructions(List<Instruction> entireInstructions, Map<String, List<Instruction>> takenInstructions, List<Instruction> selectedInstructions, User userInfo) {
        List <Instruction> removeInstructions = SuggSysFunc.sumInstructions(takenInstructions);
        removeInstructions.addAll(selectedInstructions);

        return removeInvalidInstructions(entireInstructions, removeInstructions);
    }

    public Map<String, List<Instruction>> extxNcssInstructions(Map<String, List<CourseEnums>> remainCourses, List<Instruction> validInstructions) {
        Integer idx = 0;
        Map<String, List<Instruction>> NcssInstructions = new HashMap<>();
        for ( String key : remainCourses.keySet() ) {
            String InstructionKey = getInstructionKey(key, idx);
            idx ++;

            for ( CourseEnums course : remainCourses.get(key)) {
                NcssInstructions.put(InstructionKey, validInstructions.stream()
                        .filter(x -> x.getInstructionNumber().substring(0,6).equals(course.getCourseNumber()))
                        .collect(Collectors.toList()));
            }
        }

        return NcssInstructions;
    }

    public String getInstructionKey(String key, Integer idx) {
        String InstructionKey;

        if ( key.equals("1전공")) { InstructionKey = "FM" + idx.toString(); }
        else if (key.equals("이중전공")) { InstructionKey = "SM" + idx.toString(); }
        else if (key.equals("교양")) {InstructionKey = "LA" + idx.toString(); }
        else {InstructionKey = null;}

        return InstructionKey;
    }


    public List<Instruction> removeInvalidInstructions(List<Instruction> entireInstructions, List<Instruction> removeInstructions) {
        List<String> removeCourseNumber = new ArrayList<>();
        removeInstructions.stream().forEach(x -> removeCourseNumber.add(x.getInstructionNumber()));
        return entireInstructions.stream()
                .filter(x -> !removeCourseNumber.contains(x.getInstructionNumber().substring(0,6)))
                .collect(Collectors.toList()); // 기존에 선택한 과목들 삭제
    }

}
