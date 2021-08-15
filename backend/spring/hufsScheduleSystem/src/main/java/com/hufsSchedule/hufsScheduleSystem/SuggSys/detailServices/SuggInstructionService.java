package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.google.common.collect.Sets;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Apriori;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.AssociationRule;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.AssociationRuleObj;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;

import java.util.*;
import java.util.stream.Collectors;

public class SuggInstructionService {
    public static List<WeightInstruction> initValidInstructions(List<Instruction> entireInstructions, List<Instruction> takenInstructions,
                                                                List<Instruction> selectedInstructions, List<Instruction> abandonInstructions, User userInfo) {
        List <Instruction> removeInstructions = new ArrayList<>();
        removeInstructions.addAll(takenInstructions);
        removeInstructions.addAll(selectedInstructions);
        removeInstructions.addAll(abandonInstructions);

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

    public static void applyNcssInstructions(Map<String, List<CourseEnums>> remainCourses, List<WeightInstruction> validInstructions) {
        List<CourseEnums> flattenCourses = new ArrayList<>();
        for (String key : remainCourses.keySet()) {
            flattenCourses.addAll(remainCourses.get(key));
        }
        // 에러날거같은데
        Float one = new Float(1.1);
        for ( CourseEnums course : flattenCourses ){
            validInstructions.stream()
                    .filter(x -> x.getInstruction().getInstructionNumber().substring(0,6).equals(course.getCourseNumber()))
                    .forEach(x -> x.setWeight(one));
        }
    }


    public static List<Instruction> removeInstructionsByList(List<Instruction> entireInstructions, List<Instruction> removeInstructions) {
        List<String> removeCourseNumber = new ArrayList<>();
        removeInstructions.stream().forEach(x -> removeCourseNumber.add(x.getInstructionNumber().substring(0,6)));
        return entireInstructions.stream()
                .filter(x -> !removeCourseNumber.contains(x.getInstructionNumber().substring(0,6)))
                .collect(Collectors.toList()); // 기존에 선택한 과목들 삭제
    }

    public static void tuneInstructionWeights(List<WeightInstruction> validInstruction, Map<String, List<CourseEnums>> remainCourses,
                                              User userInfo, List<List<TimetableDto.findInstructionCode>> dataset) {
        // 1. apriori 결과
        // 2. choosed 배수 따라 조정
        // 3. 전필

        for (List<TimetableDto.findInstructionCode> data : dataset) {
             applyAssociationRule(validInstruction, userInfo.getUserId(), data);
        }
        applyCrowdedInstructions(validInstruction);
        applyNcssInstructions(remainCourses, validInstruction); // 전필

        for (WeightInstruction inst : validInstruction) {
            if (inst.getInstruction().getInstructionNumber().equals("T07403102")) {
                inst.setWeight((float)1.2);
            }
        }

//        return validInstruction;
    }

    public static void applyCrowdedInstructions(List<WeightInstruction> validInstructions) {
        validInstructions.stream()
                .filter(x -> GrdCondEct.getInteger(x.getInstruction().getNumberOfPeople()) <= x.getInstruction().getChoosed() * 1.5)
                .forEach(x -> x.setWeight(x.getWeight()/2));
    }

    public static Set<Set<String>> getTransactions(List<TimetableDto.findInstructionCode> dataset, Long userId) {
        Set<Set<String>> transactions = new HashSet<>();
        Set<Long> Ids = new HashSet<>();
        for (TimetableDto.findInstructionCode data : dataset) {
            Long id = data.getUserId();
            if ( ! id.equals(userId)) { Ids.add(data.getUserId()); }
        } // get unique IDs without currentUserID

        System.out.println("id size : " + Ids.size());
        for (Long id : Ids) {
            Set<String> set = new HashSet<>();
            for (TimetableDto.findInstructionCode data : dataset) {
                if (data.getUserId().equals(id)) {
                    set.add(data.getInstructionNumber().substring(0,6));
                }
            }
            transactions.add(set);
        }
        System.out.println(transactions.size());
        return transactions;
    }

    public static Set<String> getUserTransaction(List<TimetableDto.findInstructionCode> dataset, Long userId) {
        Set<String> set = new HashSet<>();
        for (TimetableDto.findInstructionCode data : dataset) {
            if (data.getUserId().equals(userId)) {
                set.add(data.getInstructionNumber().substring(0,6));
            }
        }

        return set;
    }

    public static void applyAssociationRule(List<WeightInstruction> instructions, Long userId, List<TimetableDto.findInstructionCode> data) {
        // Apriori
        Set<Set<String>> transactions = getTransactions(data, userId);
        if (transactions.size() == 0) { return; }

        Float minSupport = (float)0.5;
        Apriori apriori = new Apriori(minSupport, transactions);
        apriori.run();
        // Association rule
        String metric = "lift";
        Float minLift = (float)1.0;
        AssociationRule associationRule = new AssociationRule(apriori.getResult(), metric, minLift);
        associationRule.run();


        Set<String> userTransaction = getUserTransaction(data, userId);
        Set<String> userNotTransaction = Sets.difference(apriori.createSet(transactions), userTransaction);

        System.out.println(" real transaction : " + transactions);
        System.out.println(" user transaction : " + userTransaction);
        System.out.println(" user not transaction : " + userNotTransaction);
        Map<String, Float> confidenceMap = new HashMap<>();
        for (String item : userNotTransaction) { // 듣지 않은 강의 loop를 돌면서 confidence 평균을 map에 input
            Integer count = new Integer(0);
            Float sum = new Float(0.0);
            for (AssociationRuleObj rule : associationRule.getRules()) {
                // 듣지않은 강의가 결과절이고, 조건절을 들은 적이 있다면
                if (item.equals(getString(rule.getConsequent()))
                        && userTransaction.contains(getString(rule.getAntecedent()))) {
                    sum += rule.getConfidence();
                    count++;
                }
            }
            if (count != 0) { confidenceMap.put(item, sum/(float)count); }
        }

        for (WeightInstruction instruction : instructions) { // instruction을 돌면서 가중치 부여
            String instructionNumber = instruction.getInstruction().getInstructionNumber().substring(0,6);
            Float value = confidenceMap.get(instructionNumber);
            if (value != null) { instruction.setWeight(value); }
        }

    }

    public static String getString(Set<String> set) {
        return set.iterator().next();
    }
}
