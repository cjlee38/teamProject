package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Entity.Course;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRatio;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;
import org.springframework.data.relational.core.sql.In;

import java.util.*;
import java.util.stream.Collectors;

public class SuggInstructionService {
    // 수강한과목 & 선택한과목 삭제.
    /*
    본인 전공에 해당하지 않는 과목 삭제할것인지..?
     */
    public static List<WeightInstruction> initValidInstructions(List<Instruction> entireInstructions, List<Instruction> takenInstructions, List<Instruction> selectedInstructions, User userInfo) {
        List <Instruction> removeInstructions = new ArrayList<>();
        removeInstructions.addAll(takenInstructions);
        removeInstructions.addAll(selectedInstructions);

        List<String> names = new ArrayList<>(Arrays.asList(userInfo.getMajor(), userInfo.getSecondMajor(), userInfo.getMinor(), "교양"));
        if (userInfo.getTeaching()) {
            names.add("교육학");
        }


        removeInstructions.addAll(entireInstructions.stream()
                .filter(x -> !names.contains(x.getDept()))
                .collect(Collectors.toList())
        ); // 1전공(e.g. 경영학전공), 이중전공(융복합소프트웨어전공), 부전공, 교육학, 교양에 포함되지 않는 과목돌은 삭제
        List<Instruction> removed =  removeInstructionsByList(entireInstructions, removeInstructions);

        return addWeightToInstructions(removed, new Float(0));

    }

    public static List<WeightInstruction> addWeightToInstructions(List<Instruction> instructions, Float weight) {
        List<WeightInstruction> weighted = new ArrayList<>();
        for (Instruction instruction : instructions) {
            weighted.add(new WeightInstruction(weight, instruction));
        }
        return weighted;
    }
    public static List<WeightInstruction> addWeigthToNcssInstructions(Map<String, List<CourseEnums>> remainCourses, List<WeightInstruction> validInstructions) {
        List<CourseEnums> flattenCourses = new ArrayList<>();
        for (String key : remainCourses.keySet()) {
            flattenCourses.addAll(remainCourses.get(key));
        }
        // 에러날거같은데
        Float one = new Float(1);
        for ( CourseEnums course : flattenCourses ){
            validInstructions.stream()
                    .filter(x -> x.getInstruction().getInstructionNumber().substring(0,6).equals(course.getCourseNumber()))
                    .forEach(x -> x.setWeight(one));
        }

        return validInstructions;

    }


    public static List<Instruction> removeInstructionsByList(List<Instruction> entireInstructions, List<Instruction> removeInstructions) {
        List<String> removeCourseNumber = new ArrayList<>();
        removeInstructions.stream().forEach(x -> removeCourseNumber.add(x.getInstructionNumber()));
        return entireInstructions.stream()
                .filter(x -> !removeCourseNumber.contains(x.getInstructionNumber().substring(0,6)))
                .collect(Collectors.toList()); // 기존에 선택한 과목들 삭제
    }


}
